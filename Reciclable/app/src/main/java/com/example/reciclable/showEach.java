package com.example.reciclable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class showEach extends AppCompatActivity {

    TextView number, email, name;
    Button delete, back;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.person);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        number = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        image = findViewById(R.id.imageView);

        String n = getIntent().getStringExtra("name");
        String e = getIntent().getStringExtra("email");
        String p = getIntent().getStringExtra("phone");

        name.setText(n);
        email.setText(e);
        number.setText(p);

        String school = e;

        if(school.equals("University of Alabama"))
            image.setImageResource(R.drawable.alabama);
        else if(school.equals("University of Georgia"))
            image.setImageResource(R.drawable.georgia);
        else if(school.equals("University of Missouri"))
            image.setImageResource(R.drawable.missouri);
        else if(school.equals("University of Tennessee"))
            image.setImageResource(R.drawable.tennessee);
        else if(school.equals("University of Arkansas"))
            image.setImageResource(R.drawable.arkansas);
        else if(school.equals("University of South Carolina"))
            image.setImageResource(R.drawable.usc);
        else if(school.equals("University of Mississippi"))
            image.setImageResource(R.drawable.olemiss);
        else if(school.equals("Auburn University"))
            image.setImageResource(R.drawable.auburn);
        else if(school.equals("Texas A&M University"))
            image.setImageResource(R.drawable.texasam);
        else if(school.equals("University of Florida"))
            image.setImageResource(R.drawable.florida);
        else if(school.equals("SEC"))
            image.setImageResource(R.drawable.sec);
        else if (school.equals("University of Kentucky"))
            image.setImageResource(R.drawable.kentucky);
        else if (school.equals("Louisiana State University"))
            image.setImageResource(R.drawable.lsu);
        else
            image.setImageResource(R.drawable.auburn);

        delete.setOnClickListener(evento);
        back.setOnClickListener(evento);


    }


    private View.OnClickListener evento = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view == delete) {
                Intent intent = new Intent(showEach.this, MainActivity.class);
                intent.putExtra("from", "delete");
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phone", number.getText().toString());
                setResult(RESULT_OK,intent);
                finish();

            } else if (view == back) {
                /*Intent intent = new Intent(showEach.this, MainActivity.class);
                intent.putExtra("from", "back");
                startActivity(intent);*/
                finish();
            }
        }
    };
}


