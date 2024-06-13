package com.example.project2024_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnyoga = findViewById(R.id.btnYoga);
//요가
        btnyoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YogaTeacher.class);
                startActivity(intent);
            }
        });
//필라테스 선생
        Button btnpila = findViewById(R.id.btnPila);

        btnpila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PilatesTeacher.class);
                startActivity(intent);
            }
        });

        Button btnhealth = findViewById(R.id.btnHealth);

        btnhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HealthTeacher.class);
                startActivity(intent);
            }
        });

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