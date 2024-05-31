package com.example.project2024_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnyoga = findViewById(R.id.btnYoga);

        btnyoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), selectwhoyoga.class);
                startActivity(intent);
            }
        });

        Button btnpila = findViewById(R.id.btnPila);

        btnpila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), selectwhopila.class);
                startActivity(intent);
            }
        });

        Button btnhealth = findViewById(R.id.btnHealth);

        btnhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), selectwhohealth.class);
                startActivity(intent);
            }
        });

    }
}