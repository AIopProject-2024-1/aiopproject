package com.example.project2024_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_teacher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView hvideo1 = findViewById(R.id.healthvideo1);
        TextView hvideo2 = findViewById(R.id.healthvideo2);

        hvideo1.setOnClickListener(v -> openCameraActivity("Health Video 1"));
        hvideo2.setOnClickListener(v -> openCameraActivity("Health Video 2"));
    }

    // activity_cameraai로 이동하며 선택한 영상 이름 전달
    private void openCameraActivity(String videoName) {
        Intent intent = new Intent(this, cameraai.class);
        intent.putExtra("VIDEO_NAME", videoName);
        startActivity(intent);
    }
}