package com.example.practica13_controlespersonalizados2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        FechaControl fechaControl = findViewById(R.id.ctrlFecha);
        //fechaControl.setFecha(26, 11, 2025);
        fechaControl.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onDateChange(String dia, String mes, String anio) {
                Toast.makeText(getApplication(), "fecha: " + dia + "/" + mes + "/" + anio, Toast.LENGTH_SHORT).show();
            }
        });


    }
}