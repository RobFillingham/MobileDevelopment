package com.example.practica18sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private LinearLayout linearLayout;
    private TextView textViews[][] = new TextView[20][3];
    private SensorManager sensorManager;
    private Sensor sensors[] = new Sensor[20];
    private List<Sensor> listSensors;
    private TextView textView1;

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

        try{
            linearLayout = findViewById(R.id.linearLayout);
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            listSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            int n=0;
            textView1 = findViewById(R.id.textView);
            textView1.setText("Sensores: "+listSensors.size() + "");

            for (Sensor sensor : listSensors){
                sensors[n] = sensor;
                TextView inTextView = new TextView(this);
                inTextView.setText(sensor.getName());
                linearLayout.addView(inTextView);
                LinearLayout inLinearLayout = new LinearLayout(this);
                linearLayout.addView(inLinearLayout);

                for(int i=0; i<3; i++){
                    textViews[n][i] = new TextView(this);
                    textViews[n][i].setText("?");
                    textViews[n][i].setWidth(300);
                }
            }
        }catch(Exception e){

            }
        }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try{
            synchronized(this){
                int n = 0;
                for (Sensor sensor : listSensors){
                    if(event.sensor == sensor) {
                        for (int i = 0; i < event.values.length; i++) {
                            textViews[n][i].setText(Math.round(event.values[i]*100d)/100d + "");
                        }
                    }
                    n++;
                }
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}