package com.example.actividad15_controlpersonalizado;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button plus, minus;
    Galaxy galaxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        galaxy = findViewById(R.id.galaxy);

        plus.setOnClickListener(v -> {
            galaxy.setProgress(galaxy.getProgress() + 0.01F);
        });

        minus.setOnClickListener(v -> {
            galaxy.setProgress(galaxy.getProgress() - 0.01F);
        });




    }




}