package com.example.project2024_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonLayout extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttonlayout);

        ImageButton btnhome = findViewById(R.id.btnhome);
        ImageButton btnsetting = findViewById(R.id.btnsetting);
        ImageButton btnmypage = findViewById(R.id.btnmypage);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), setting.class);
                startActivity(intent);
            }
        });

        btnmypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mypage.class);
                startActivity(intent);
            }
        });
    }
}
