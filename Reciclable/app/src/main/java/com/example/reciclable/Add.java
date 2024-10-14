package com.example.reciclable;

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

public class Add extends AppCompatActivity {

    EditText name, email, phone;
    Button add, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.insertar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);
        back = findViewById(R.id.back);

        add.setOnClickListener(evento);
        back.setOnClickListener(evento);

    }

    private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == back) {
                finish();
            }else if(view == add){
                Intent intent = new Intent(Add.this, MainActivity.class);
                intent.putExtra("from", "add");
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };

}
