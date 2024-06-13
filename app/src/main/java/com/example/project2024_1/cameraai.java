package com.example.project2024_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseLandmark;
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions;

import java.util.concurrent.ExecutionException;

public class cameraai extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA_PERMISSION = 10;
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 1;

    private PreviewView previewView;
    private PoseDetector poseDetector;

    private VideoView videoview;

    private Paint paint;

    //basic setting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameraai);

        paint = new Paint();
        paint.setColor(0xFFFFFFFF); // 변경: 색상을 흰색으로 설정
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);

        // Request camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_PERMISSION);
        } else {
            startCamera(); // 추가: 권한이 이미 승인된 경우 카메라 시작
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPostNotificationsPermission(); // 추가: TIRAMISU 버전 이상일 경우 알림 권한 요청
        }

        // Video setup
        videoview = findViewById(R.id.videopreView);
        String vname = getIntent().getStringExtra("VIDEO_NAME");
        String videoPath = null;
        if ("Yoga Video 1".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/yoga_video_1";
        }
        if ("Yoga Video 2".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/yoga_video_2";
        }
        if ("Pilates Video 1".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/pila_video_2";
        }
        if ("Pilates Video 2".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/pila_video_2";
        }
        if ("Health Video 1".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/health_video_1";
        }
        if ("Health Video 2".equals(vname)){
            videoPath = "android.resource://" + getPackageName() + "/raw/health_video_2";
        }
        Uri videoURI = Uri.parse(videoPath);
        videoview.setVideoURI(videoURI);
        videoview.start();

        previewView = findViewById(R.id.userpreView);

        PoseDetectorOptions options =
                new PoseDetectorOptions.Builder()
                        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                        .build();

        poseDetector = PoseDetection.getClient(options);
    }

    //권한 요청
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void requestPostNotificationsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    REQUEST_CODE_POST_NOTIFICATIONS);
        }
    }

    //카메라 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera(); // 추가: 카메라 권한이 승인된 경우 카메라 시작
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_LONG).show(); // 추가: 카메라 권한 거부 시 메시지 표시
            }
        } else if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("CameraAI", "Post Notifications 권한 허용"); // 추가: 알림 권한 허용 로그
            } else {
                Log.d("CameraAI", "Post Notifications 권한 거부됨"); // 추가: 알림 권한 거부 로그
                Toast.makeText(this, "알림 권한 필요", Toast.LENGTH_LONG).show(); // 추가: 알림 권한 필요 메시지
            }
        }
    }

    //카메라 실행
    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Log.e("CameraAI", "실행안됨", e); // 추가: 예외 처리 로그
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), imageProxy -> {
            processImageProxy(imageProxy);
        });

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }

    //detect pose in camera
    @OptIn(markerClass = ExperimentalGetImage.class)
    private void processImageProxy(ImageProxy imageProxy) {
        InputImage image = InputImage.fromMediaImage(imageProxy.getImage(), imageProxy.getImageInfo().getRotationDegrees());

        poseDetector.process(image)
                .addOnSuccessListener(pose -> {
                    drawPosesOnCamera(pose); // Pose detection successful
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Log.e("CameraAI", "실패", e); // 추가: 실패 로그
                })
                .addOnCompleteListener(task -> {
                    imageProxy.close(); // 추가: 작업 완료 후 이미지 프로시 닫기
                });
    }

    private void drawPosesOnCamera(Pose pose) {
        previewView.post(() -> {
            PoseOverlayView poseOverlayView = new PoseOverlayView(this, pose, paint); // 추가: PoseOverlayView 생성
            previewView.getOverlay().clear(); // 추가: 기존 오버레이 지우기
            previewView.getOverlay().add(poseOverlayView); // 추가: 새로운 오버레이 추가
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (poseDetector != null) {
            poseDetector.close(); // 추가: poseDetector 닫기
        }
    }

    // 추가: PoseOverlayView 클래스 정의
    private static class PoseOverlayView extends View {
        private Pose pose;
        private Paint paint;

        public PoseOverlayView(Context context, Pose pose, Paint paint) {
            super(context);
            this.pose = pose;
            this.paint = paint;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (pose != null) {
                for (PoseLandmark landmark : pose.getAllPoseLandmarks()) {
                    PointF point = landmark.getPosition();
                    canvas.drawCircle(point.x, point.y, 10, paint);
                }
            }
        }
    }
}
