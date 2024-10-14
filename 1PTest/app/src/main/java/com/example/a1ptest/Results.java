package com.example.a1ptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Results extends AppCompatActivity {

    //Elemenots que mostrarán acad paso del calculo del ISR
    TextView total, first, second, third, fourth, fith, sixth, seventh, eighth, ninth;

    //Variables que irán almacenando cada paso del calculo
    double income, limiteInferior, diferencia, tasa, impuestoMarginal, cuotaFija,impuestoARetener, percepcionMenosImpuestos, ingresoMensual, subsidio, totalN;

    String period;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializamos los elementos
        total = findViewById(R.id.total);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fith = findViewById(R.id.fifth);
        sixth = findViewById(R.id.sixth);
        seventh = findViewById(R.id.seventh);
        eighth = findViewById(R.id.eighth);
        ninth = findViewById(R.id.ninth);

        //La actividad es llamada por otra actividad, extraemos los datos enviados:
        if(getIntent().getExtras()!=null) {
            Intent intent = getIntent();
            period = (String) (intent.getStringExtra("Period"));
            String incomeS = (String) (intent.getStringExtra("Income"));
            income = Double.parseDouble(incomeS);
        }


        //El siguiente switch determina el periodo y luego con una serie de ifs cual rango de valores para el limite inferior, cuota fija y tasa se usará
        switch (period){
            case "Diario":
                //En cada periodo calculamos el ingreso mensual para saber si habrá subsidio
                ingresoMensual = income*30;
                if(income <= 24.54){
                    limiteInferior=0.01;
                    cuotaFija = 0;
                    tasa = 0.0192;
                } else if (income <= 208.29) {
                    limiteInferior=24.55;
                    cuotaFija = 0.47;
                    tasa = 0.0640;
                } else if (income <= 366.05) {
                    limiteInferior=208.3;
                    cuotaFija = 0;
                    tasa = 0.0192;
                }else if(income <= 425.52){
                    limiteInferior=366.06;
                    cuotaFija = 29.40;
                    tasa = 0.16;
                } else if (income <= 509.46) {
                    limiteInferior=425.53;
                    cuotaFija = 38.91;
                    tasa = 0.1792;
                } else if (income <= 1027.52) {
                    limiteInferior=509.47;
                    cuotaFija = 53.95;
                    tasa = 0.2136;
                }else if(income <= 1619.51){
                    limiteInferior=1027.53;
                    cuotaFija = 164.61;
                    tasa = 0.2352;
                }else if(income <= 3091.90){
                    limiteInferior=1619.52;
                    cuotaFija = 303.85;
                    tasa = 0.3;
                }else if(income <= 4122.54){
                    limiteInferior=3091.91;
                    cuotaFija = 745.56;
                    tasa = 0.32;
                } else if (income <= 12367.62) {
                    limiteInferior=4122.55;
                    cuotaFija = 1075.37;
                    tasa = 0.34;
                }else{
                    limiteInferior=12367.63;
                    cuotaFija = 3878.69;
                    tasa = 0.35;
                }
                break;
            case "Semanal":
                ingresoMensual = income*4;
                if(income <= 171.78){
                    limiteInferior=0.01;
                    cuotaFija = 0;
                    tasa = 0.0192;
                } else if (income <= 1458.03) {
                    limiteInferior=171.79;
                    cuotaFija = 3.29;
                    tasa = 0.0640;
                } else if (income <= 2562.35) {
                    limiteInferior=1458.04;
                    cuotaFija = 85.61;
                    tasa = 0.0192;
                }else if(income <= 2978.64){
                    limiteInferior=2562.36;
                    cuotaFija = 205.80;
                    tasa = 0.16;
                } else if (income <= 3566.22) {
                    limiteInferior=2978.65;
                    cuotaFija = 272.37;
                    tasa = 0.1792;
                } else if (income <= 7192.64) {
                    limiteInferior=3566.23;
                    cuotaFija = 377.65;
                    tasa = 0.2136;
                }else if(income <= 11336.57){
                    limiteInferior=7192.65;
                    cuotaFija = 1152.27;
                    tasa = 0.2352;
                }else if(income <= 21643.3){
                    limiteInferior=11336.58;
                    cuotaFija = 2126.95;
                    tasa = 0.3;
                }else if(income <= 28857.78){
                    limiteInferior=21643.31;
                    cuotaFija = 5218.92;
                    tasa = 0.32;
                } else if (income <= 86573.34) {
                    limiteInferior=28857.79;
                    cuotaFija = 7527.59;
                    tasa = 0.34;
                }else{
                    limiteInferior=86573.35;
                    cuotaFija = 27150.83;
                    tasa = 0.35;
                }
                break;
            case "Decenal":
                ingresoMensual = income*3;
                if(income <= 254.4){
                    limiteInferior=0.01;
                    cuotaFija = 0;
                    tasa = 0.0192;
                } else if (income <= 2082.9) {
                    limiteInferior=254.41;
                    cuotaFija = 4.7;
                    tasa = 0.0640;
                } else if (income <= 3669.5) {
                    limiteInferior=2082.91;
                    cuotaFija = 122.3;
                    tasa = 0.0192;
                }else if(income <= 4255.2){
                    limiteInferior=3669.51;
                    cuotaFija = 294.0;
                    tasa = 0.16;
                } else if (income <= 5094.6) {
                    limiteInferior=4255.21;
                    cuotaFija = 389.1;
                    tasa = 0.1792;
                } else if (income <= 10275.2) {
                    limiteInferior=5094.61;
                    cuotaFija = 539.5;
                    tasa = 0.2136;
                }else if(income <= 16195.1){
                    limiteInferior=10275.21;
                    cuotaFija = 1646.1;
                    tasa = 0.2352;
                }else if(income <= 30919.0){
                    limiteInferior=16195.11;
                    cuotaFija = 3038.5;
                    tasa = 0.3;
                }else if(income <= 41225.4){
                    limiteInferior=30919.01;
                    cuotaFija = 7455.6;
                    tasa = 0.32;
                } else if (income <= 123676.2) {
                    limiteInferior=41225.41;
                    cuotaFija = 10753.7;
                    tasa = 0.34;
                }else{
                    limiteInferior=123676.21;
                    cuotaFija = 38786.9;
                    tasa = 0.35;
                }

                break;
            case "Quincenal":
                ingresoMensual = income*2;
                if(income <= 368.1){
                    limiteInferior=0.01;
                    cuotaFija = 0;
                    tasa = 0.0192;
                } else if (income <= 3124.35) {
                    limiteInferior=368.11;
                    cuotaFija = 7.05;
                    tasa = 0.0640;
                } else if (income <= 5490.75) {
                    limiteInferior=3124.36;
                    cuotaFija = 183.45;
                    tasa = 0.0192;
                }else if(income <= 6382.8){
                    limiteInferior=5490.76;
                    cuotaFija = 441;
                    tasa = 0.16;
                } else if (income <= 7641.9) {
                    limiteInferior=6382.81;
                    cuotaFija = 583.65;
                    tasa = 0.1792;
                } else if (income <= 15412.8) {
                    limiteInferior=7641.91;
                    cuotaFija = 809.25;
                    tasa = 0.2136;
                }else if(income <= 24292.65){
                    limiteInferior=15412.81;
                    cuotaFija = 2469.15;
                    tasa = 0.2352;
                }else if(income <= 46378.5){
                    limiteInferior=24292.66;
                    cuotaFija = 4557.75;
                    tasa = 0.3;
                }else if(income <= 61838.1){
                    limiteInferior=46378.51;
                    cuotaFija = 11183.4;
                    tasa = 0.32;
                } else if (income <= 185514.3) {
                    limiteInferior=61838.11;
                    cuotaFija = 16130.55;
                    tasa = 0.34;
                }else{
                    limiteInferior=86573.35;
                    cuotaFija = 58180.35;
                    tasa = 0.35;
                }
                break;

            case "Mensual":
                ingresoMensual = income;
                if(income <= 746.04){
                    limiteInferior=0.01;
                    cuotaFija = 0;
                    tasa = 0.0192;
                } else if (income <= 6332.05) {
                    limiteInferior=746.05;
                    cuotaFija = 14.32;
                    tasa = 0.0640;
                } else if (income <= 11128.01) {
                    limiteInferior=6332.06;
                    cuotaFija = 371.83;
                    tasa = 0.0192;
                }else if(income <= 12935.82){
                    limiteInferior=11128.02;
                    cuotaFija = 893.63;
                    tasa = 0.16;
                } else if (income <= 15487.71) {
                    limiteInferior=12935.83;
                    cuotaFija = 1182.88;
                    tasa = 0.1792;
                } else if (income <= 31236.49) {
                    limiteInferior=15487.72;
                    cuotaFija = 1640.18;
                    tasa = 0.2136;
                }else if(income <= 49233){
                    limiteInferior=31236.50;
                    cuotaFija = 5004.12;
                    tasa = 0.2352;
                }else if(income <= 93993.9){
                    limiteInferior=49233.01;
                    cuotaFija = 9236.89;
                    tasa = 0.3;
                }else if(income <= 125325.2){
                    limiteInferior=93993.91;
                    cuotaFija = 22665.17;
                    tasa = 0.32;
                } else if (income <= 375975.61) {
                    limiteInferior=125325.21;
                    cuotaFija = 32691.18;
                    tasa = 0.34;
                }else{
                    limiteInferior=275975.62;
                    cuotaFija = 117912.32;
                    tasa = 0.35;
                }
                break;

        }

        //Calculo del subsidio mensual

        if(ingresoMensual <= 1768.96){
            subsidio = 407.02;
        } else if (ingresoMensual <= 2653.38) {
            subsidio = 406.83;
        } else if (ingresoMensual <= 3472.84){
            subsidio = 406.62;
        } else if (ingresoMensual <= 3537.87) {
            subsidio = 392.77;
        } else if (ingresoMensual <= 4117.18) {
            subsidio = 382.46;
        } else if (ingresoMensual <= 4446.15) {
            subsidio = 354.23;
        } else if (ingresoMensual <= 5335.42) {
            subsidio = 324.87;
        } else if(ingresoMensual <= 6224.67){
            subsidio = 294.63;
        } else if (ingresoMensual <= 7113.9) {
            subsidio = 253.54;
        } else if (ingresoMensual <= 7382.33) {
            subsidio = 217.61;
        }else{
            subsidio = 0;
        }

        //Realizamos los calculos ahora que sabemos el valor de los parametros necesarios

        diferencia = income - limiteInferior;
        impuestoMarginal = diferencia * tasa;
        impuestoARetener = impuestoMarginal + cuotaFija;
        percepcionMenosImpuestos = income - impuestoARetener;


        //mostramos los resultados
        Log.d("mine", "made it before income");
        //String.format nos permite agregar las comas y puntos decimales a las cifras
        first.setText("Ingresos: $"+String.format("%,.2f", income));
        Log.d("mine", "made it past income");
        second.setText("Limite Inferior: $"+String.format("%,.2f", limiteInferior));
        Log.d("mine", "made it past limite inferior");
        third.setText("Diferencia: $"+String.format("%,.2f", diferencia));
        fourth.setText("Tasa: "+String.format("%,.2f", tasa*100)+"%");
        Log.d("mine", "made it past tasa");
        fith.setText("Impuesto Marginal: $"+String.format("%,.2f", impuestoMarginal));
        Log.d("mine", "made it past marginal");
        sixth.setText("Cuota Fija: $"+String.format("%,.2f", cuotaFija));
        Log.d("mine", "made it past cuotafija");
        seventh.setText("Impuesto a Retener: $"+String.format("%,.2f", impuestoARetener));
        Log.d("mine", "made it past retener");
        eighth.setText("Percepción Menos Impuestos: $"+String.format("%,.2f", percepcionMenosImpuestos));
        Log.d("mine", "made it past percepcion");
        ninth.setText("Subsidio Mensual: $"+String.format("%,.2f", subsidio));
        Log.d("mine", "made it past subsidio");
        total.setText("$"+String.format("%,.2f", impuestoARetener)+"MXN");
        Log.d("mine", "made it past total");


    }

}
