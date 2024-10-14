package com.example.llamarotraactividad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText txtNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.button);
        txtNombre = findViewById(R.id.input);
        btn.setOnClickListener(evento);

    }

    private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btn){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("Nombre", txtNombre.getText().toString());
                startActivity(intent);
            }
        }
    };
}