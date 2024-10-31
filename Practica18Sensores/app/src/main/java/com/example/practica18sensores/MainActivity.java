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


    private TextView xTextView;
    private TextView yTextView;
    private TextView zTextView;
    private Sensor accelerometer, gyroscope;
    private Board board;
    private double Ax, Ay, Az, Gx, Gy, Gz;
    private boolean both=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.drawing_board);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        xTextView = findViewById(R.id.textView1);
        xTextView.setText("X:");
        yTextView = findViewById(R.id.textView2);
        yTextView.setText("Y:");
        zTextView = findViewById(R.id.textView3);
        zTextView.setText("Z:");
        board = findViewById(R.id.board);







        try{

            /*
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

                TextView xTextView = new TextView(this);
                xTextView.setText("   X:");
                inLinearLayout.addView(xTextView);
                inLinearLayout.addView(textViews[n][0]);

                TextView yTextView = new TextView(this);
                yTextView.setText("   Y:");
                inLinearLayout.addView(yTextView);
                inLinearLayout.addView(textViews[n][1]);

                TextView zTextView = new TextView(this);
                zTextView.setText("   Z:");
                inLinearLayout.addView(zTextView);
                inLinearLayout.addView(textViews[n][2]);

                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

                n++;

                */

            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            if (sensorManager != null) {
                accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
                sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI);
            }



        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }



    }







    @Override
    public void onSensorChanged(SensorEvent event) {
            synchronized(this){
                /*int n = 0;
                for (Sensor sensor : listSensors){
                    if(event.sensor == sensor) {
                        try{
                            for (int i = 0; i < event.values.length; i++) {
                                textViews[n][i].setText(Math.round(event.values[i]*100d)/100d + "");
                            }
                        }catch(Exception e){
                            //Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                    n++;
                    if(n>= listSensors.size())
                        break;

                }*/

                if(event.sensor == accelerometer ){
                    Ax = Math.round(event.values[0]*100d)/100d;
                    Ay = Math.round(event.values[1]*100d)/100d;
                    Az = Math.round(event.values[2]*100d)/100d;

                }else if(event.sensor == gyroscope){
                    Gx = Math.round(event.values[0]*100d)/100d;
                    Gy = Math.round(event.values[1]*100d)/100d;
                    Gz = Math.round(event.values[2]*100d)/100d;
                }
                board.sensorMovement(Ax, Ay, Az, Gx, Gy, Gz);
                xTextView.setText("X: " + Ax);
                yTextView.setText("Y: " + Ay);
                zTextView.setText("Z: " + Az);


            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {





    }
}