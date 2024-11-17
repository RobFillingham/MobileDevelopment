package com.example.practica20_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.Date;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Button button;

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

        TextView salida = findViewById(R.id.salida);

        DBSQLite aSQLite = new DBSQLite(this);
        aSQLite.guardaDatos("Juan", (new Date()).getTime());
        aSQLite.guardaDatos("Pedro", (new Date()).getTime());
        aSQLite.guardaDatos("Maria", (new Date()).getTime());

        Vector<String> datos = aSQLite.listaDatos();

        for(String str : datos){
            salida.append(str+" ");
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            aSQLite.borraDatos();
            salida.setText("");
        });

    }

}