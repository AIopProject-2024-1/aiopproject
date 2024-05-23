package com.example.project2024_1;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class selectwho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectwho);

        LinearLayout scrollimg = findViewById(R.id.scrollimg);

        int[] imageResources = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

        for (int i = 0; i < imageResources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100,100));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imageResources[i]);
            scrollimg.addView(imageView);

    }
}