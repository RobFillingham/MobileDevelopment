package com.example.practica10_listviewlamda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

        ListView listview = findViewById(R.id.lvFrutas);
        Producto[] datos = new Producto[]{
                new Producto("Auburn", "War Eagle!", 1),
                new Producto("Alabama", "Roll Tide!", 20),
                new Producto("Georgia", "Go Dawgs!", 3),
                new Producto("LSU", "Geux Tigers!", 4),
                new Producto("Florida", "Chomp Chomp!", 5),
                new Producto("Tennessee", "Go Vols!", 5)

        };

        ProductoAdapter adaptadorDatos = new ProductoAdapter(this, datos);
        listview.setAdapter(adaptadorDatos);
        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            Producto prod = (Producto) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
            intent.putExtra("producto", prod);
            startActivity(intent);

            //Toast.makeText(getApplicationContext(), "ListView "+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
        });


        /*String []frutas = {"Cereza", "Ciruela", "Higo", "Manzana", "Pera", "Uva"};
        ArrayAdapter<String> adaptadorFrutas =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frutas);
        listview.setAdapter(adaptadorFrutas);

        listview.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), "ListView "+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
        });*/

    }


}