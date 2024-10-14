package com.example.a1ptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Los tres elementos de la actividad
    Button button;
    EditText income;
    String period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.inputs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializamos los valores  del selector con el arrayadapter

        String[] datos = {"Diario", "Semanal", "Decenal", "Quincenal", "Mensual"};
        ArrayAdapter<String> adaptadorDatos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        Spinner spin = findViewById(R.id.spin);
        adaptadorDatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adaptadorDatos);
        spin.setOnItemSelectedListener(evento2);
        spin.setSelection(0);

        button = findViewById(R.id.button);
        button.setOnClickListener(evento);
        period="Diario"; //valor por default en caso de que no se haga una seleccion y se presione continuar
        income = findViewById(R.id.income);
    }

    //manejador de eventos del boton continuar
    private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == button && !income.getText().toString().isEmpty()){
                Intent intent = new Intent(MainActivity.this, Results.class);
                //pasamos los dos datos ncesarios para calcular el ISR a la actividad que muestra los resultados
                intent.putExtra("Period", period);
                intent.putExtra("Income", income.getText().toString());
                startActivity(intent);
            }
        }
    };

    //Cuando se selecciona algo del seleccionador guardamos el periodo.
    private AdapterView.OnItemSelectedListener evento2 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            period = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}