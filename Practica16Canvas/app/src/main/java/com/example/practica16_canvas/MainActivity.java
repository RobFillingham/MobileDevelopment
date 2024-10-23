package com.example.practica16_canvas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button punto, linea, cuadrado, arco, circulo, ovalo, rectangulo, texto;
    CLienzo lienzo;

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
        
        lienzo = findViewById(R.id.CLienzo);

        punto = findViewById(R.id.punto);
        linea = findViewById(R.id.linea);
        cuadrado = findViewById(R.id.cuadrado);
        arco = findViewById(R.id.arco);
        circulo = findViewById(R.id.circulo);
        ovalo = findViewById(R.id.ovalo);
        rectangulo = findViewById(R.id.rectangulo);
        texto = findViewById(R.id.texto);

        punto.setOnClickListener(evento);
        linea.setOnClickListener(evento);
        cuadrado.setOnClickListener(evento);
        arco.setOnClickListener(evento);
        circulo.setOnClickListener(evento);
        ovalo.setOnClickListener(evento);
        rectangulo.setOnClickListener(evento);
        texto.setOnClickListener(evento);


    }

    public View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.punto){
                lienzo.setSelectedShape(1);
            }else if(v.getId() == R.id.linea){
                lienzo.setSelectedShape(2);
            }else if(v.getId() == R.id.cuadrado){
                lienzo.setSelectedShape(3);
            }else if(v.getId() == R.id.arco){
                lienzo.setSelectedShape(4);
            }else if(v.getId() == R.id.circulo){
                lienzo.setSelectedShape(5);
            }else if(v.getId() == R.id.ovalo){
                lienzo.setSelectedShape(6);
            }else if(v.getId() == R.id.rectangulo){
                lienzo.setSelectedShape(7);
            }else if(v.getId() == R.id.texto){
                lienzo.setSelectedShape(8);
            }
        }
    };

}