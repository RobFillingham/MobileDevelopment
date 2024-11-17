package com.example.calendar;

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


        CalendarControl calendar = findViewById(R.id.calendar);

        /*calendar.setMonth(11);
        calendar.setYear(2024);
        calendar.setDay(01);*/

        calendar.setDateListener(new DateListener() {
            @Override
            public void onDateSelected(int day, int month, int year) {
                String date = day + "/" + month + "/" + year +" LISTENER";
                Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();
            }
        });


    }
}