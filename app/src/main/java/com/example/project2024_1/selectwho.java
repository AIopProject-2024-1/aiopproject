package com.example.project2024_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class selectwho extends AppCompatActivity {

    private LinearLayout scrollTextLayout;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectwho);

        LinearLayout scrollimg = findViewById(R.id.scrollimg);
        scrollTextLayout = findViewById(R.id.scrolltext);
        inflater = LayoutInflater.from(this);

        int[] imageResources = {R.drawable.humimg1, R.drawable.humimg2, R.drawable.humimg3, R.drawable.humimg4};

        for (int i = 0; i < imageResources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imageResources[i]);
            scrollimg.addView(imageView);
        }
    }

    private void updateScrollViewContent(int layoutResource) {
        scrollTextLayout.removeAllViews();
        View newView = inflater.inflate(layoutResource, scrollTextLayout, false);
        scrollTextLayout.addView(newView);
    }
}
