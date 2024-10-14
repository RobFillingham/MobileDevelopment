package com.example.practica8refg_spinners;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.*;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    ImageView imagen, imagen2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.excercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] datos = {"Auburn", "Georgia", "Alabama", "Florida", "Tennessee"};
        ArrayAdapter<String> adaptadorDatos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        Spinner cmbOpciones1 = findViewById(R.id.cmbOpciones1);

        String[] datos2 = {"Chomp Chomp!", "Roll Tide!", "War Eagle!", "Go Vols!", "Go Dawgs!"};
        ArrayAdapter<String> adaptadorDatos2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos2);
        Spinner cmbOpciones2 = findViewById(R.id.cmbOpciones2);

        adaptadorDatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones1.setAdapter(adaptadorDatos);
        cmbOpciones1.setOnItemSelectedListener(evento1);
        cmbOpciones1.setSelection(0);



        adaptadorDatos2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones2.setAdapter(adaptadorDatos2);

        cmbOpciones2.setSelection(2);

        imagen = findViewById(R.id.imagen);
        imagen2 = findViewById((R.id.imagen2));

        imagen.setImageResource(R.drawable.smokey);
        imagen2.setImageResource(R.drawable.smokey2);


    }

    private AdapterView.OnItemSelectedListener evento2 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch(i){
                case 0:
                    imagen2.setImageResource(R.drawable.albert2);
                    Toast.makeText(getApplicationContext(), "Florida", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    imagen2.setImageResource(R.drawable.bigal2);
                    Toast.makeText(getApplicationContext(), "Alabama", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    imagen2.setImageResource(R.drawable.aubie2);
                    Toast.makeText(getApplicationContext(), "Auburn", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    imagen2.setImageResource(R.drawable.smokey2);
                    Toast.makeText(getApplicationContext(), "Tennesse", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    imagen2.setImageResource(R.drawable.hairy2);
                    Toast.makeText(getApplicationContext(), "Georgia", Toast.LENGTH_SHORT).show();
                    break;


            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(getApplicationContext(), "Sin seleccion Spinner", Toast.LENGTH_SHORT).show();
        }
    };

    private AdapterView.OnItemSelectedListener evento1 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            switch(i){
                case 0:
                    imagen.setImageResource(R.drawable.aubie);
                    break;
                case 1:
                    imagen.setImageResource(R.drawable.hairy);
                    break;
                case 2:
                    imagen.setImageResource(R.drawable.bigal);
                    break;
                case 3:
                    imagen.setImageResource(R.drawable.albert);
                    break;
                case 4:
                    imagen.setImageResource(R.drawable.smokey);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}