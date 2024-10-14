package com.example.practica13_controlespersonalizados2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaControl extends LinearLayout {
     TextView txtAnio;
     TextView txtMes;
     TextView txtDia;
     Button btnAtras;
     Button btnSiguiente;
     OnDateChangeListener listener;
     DateTimeFormatter formatter;
     LocalDate date;

    public FechaControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializar();

        /*creamos el archivo attrs.xml
            el cual contiene atributos, desde el main layout cuando creamos el elemento FechaControl le asignamos
            valores a esos atributos, el conjunto de atributos esta asignado a la clase FechaControl
            por lo que cuando creemos un objeto fecha control se le pasarán esos valores en forma de attrs, los abstraemos
            y asiganmos a los atributos correspondientes

         */

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FechaControl);
        String dia = a.getString(R.styleable.FechaControl_dia_text);
        String mes = a.getString(R.styleable.FechaControl_mes_text);
        String anio = a.getString(R.styleable.FechaControl_anio_text);

        txtDia.setText("01");
        txtMes.setText("01");
        txtAnio.setText("1900");

        if(dia != null){
            txtDia.setText(dia);
        }
        if(mes != null) {
            txtMes.setText(mes);
        }
        if(anio != null) {
            txtAnio.setText(anio);
        }

        a.recycle();
    }

    public FechaControl(Context context) {
        super(context);
        inicializar();
    }

    public void inicializar(){

        LayoutInflater li = LayoutInflater.from(getContext());
        li.inflate(R.layout.fecha_control, this, true);

        txtAnio = findViewById(R.id.txtAnio);
        txtMes = findViewById(R.id.txtMes);
        txtDia = findViewById(R.id.txtDia);
        btnAtras = findViewById(R.id.btnAtras);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        asignarEventos();

        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = txtDia.getText().toString() + "/" + txtMes.getText().toString() + "/" + txtAnio.getText().toString();
        date = LocalDate.parse(dateString, formatter);

    }

    public void asignarEventos(){
        btnAtras.setOnClickListener(evento);
        btnSiguiente.setOnClickListener(evento);
    }

    public void setFecha(int dia, int mes, int anio){
        txtDia.setText(String.valueOf(dia));
        txtMes.setText(String.valueOf(mes));
        txtAnio.setText(String.valueOf(anio));
        date = LocalDate.of(anio, mes, dia);
    }

    public void setOnDateChangeListener(OnDateChangeListener listener){
        this.listener = listener;
    }

    private OnClickListener evento = new OnClickListener() {
        @Override
        public void onClick(View v) {
            /*int dia = new Integer(txtDia.getText().toString());
            int mes = new Integer(txtMes.getText().toString());
            int anio = new Integer(txtAnio.getText().toString());*/
            LocalDate nextDate = null;
            if(v == btnAtras){
                //dia--;
                nextDate = date.minusDays(1);
            }
            if(v == btnSiguiente){
                //dia++;
                nextDate = date.plusDays(1);
            }
            if(nextDate != null) {
                date = nextDate;
                String dia = nextDate.getDayOfMonth() + "";
                String mes = nextDate.getMonthValue() + "";
                String anio = nextDate.getYear() + "";

                txtDia.setText(dia);
                txtMes.setText(mes);
                txtAnio.setText(anio);


                if (listener != null) {
                    listener.onDateChange(String.valueOf(dia), String.valueOf(mes), String.valueOf(anio));
                }
            }
        }
    };
}
