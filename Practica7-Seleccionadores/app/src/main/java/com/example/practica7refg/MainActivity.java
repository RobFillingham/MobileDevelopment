package com.example.practica7refg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    /*CheckBox chbsel;
    RadioButton rbtnOpc1, rbtnOpc2;*/

    RadioButton longitud, temperatura, masa, a, b, c, a1, b1, c1;
    EditText text, editOutput;
    Button button;
    LinearLayout first, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.conversor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*chbsel = findViewById(R.id.checkBox);
        chbsel.setOnClickListener(evento);

        RadioGroup rGroup = findViewById(R.id.rGroup);
        rbtnOpc1 = findViewById(R.id.rbtnOpc1);
        rbtnOpc2 = findViewById(R.id.rbtnOpc2);

        rGroup.clearCheck();
        rGroup.check(R.id.rbtnOpc1);

        rbtnOpc1.setOnClickListener(evento);
        rbtnOpc2.setOnClickListener(evento);*/

        longitud = findViewById(R.id.longitud);
        longitud.setOnClickListener(eventoRB);

        masa = findViewById(R.id.masa);
        masa.setOnClickListener(eventoRB);

        temperatura = findViewById(R.id.temperatura);
        temperatura.setOnClickListener(eventoRB);

        a = findViewById(R.id.a);

        b = findViewById(R.id.b);
        c = findViewById(R.id.c);

        a1 = findViewById(R.id.a1);
        b1 = findViewById(R.id.b1);
        c1 = findViewById(R.id.c1);

        text = findViewById(R.id.text);
        editOutput = findViewById(R.id.editOutput);

        button = findViewById(R.id.button);
        button.setOnClickListener(but);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);

    }

    /*private View.OnClickListener evento = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == chbsel){
                if(chbsel.isChecked())
                    chbsel.setText("Checkbox Seleccionado");
                else
                    chbsel.setText("Checkbox no seleccionado");
            }

            String opc="";
            if( view instanceof RadioButton){
                if(view == rbtnOpc1)
                    opc = "opcion 1";
                if(view == rbtnOpc2)
                    opc = "opcion 2";

                Toast.makeText(getApplicationContext(), "Opcion Seleccionada " + opc, Toast.LENGTH_SHORT).show();
            }
        }
    };*/

    private View.OnClickListener eventoRB = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton clicked = (RadioButton) view;
            String content = clicked.getText().toString();
            first.setVisibility(View.VISIBLE);
            second.setVisibility(View.VISIBLE);

            switch(content){
                case "Longitud":
                    a.setText("Metros");
                    b.setText("Centimetros");
                    c.setText("Kilometros");

                    a1.setText("Metros");
                    b1.setText("Centimetros");
                    c1.setText("Kilometros");
                break;

                case "Masa":
                    a.setText("Gramos");
                    b.setText("Centigramos");
                    c.setText("Kilogramos");

                    a1.setText("Gramos");
                    b1.setText("Centigramos");
                    c1.setText("Kilogramos");
                break;

                case "Temperatura":
                    a.setText("Centigrados");
                    b.setText("Fahrenheit");
                    c.setText("Kelvin");

                    a1.setText("Centigrados");
                    b1.setText("Fahrenheit");
                    c1.setText("Kelvin");
                break;
            }

        }
    };

    private View.OnClickListener but = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String left="", right="", input="", operacion="", numberText=text.getText().toString();
            float number=0;

            if(!numberText.isEmpty())
                number=Float.parseFloat(numberText);



            if(a.isChecked())
                left = a.getText().toString();
            else if(b.isChecked())
                left = b.getText().toString();
            else if(c.isChecked())
                left = c.getText().toString();

            if(a1.isChecked())
                right = a1.getText().toString();
            else if(b1.isChecked())
                right = b1.getText().toString();
            else if(c1.isChecked())
                right = c1.getText().toString();

            if(masa.isChecked())
                operacion = masa.getText().toString();
            else if(temperatura.isChecked())
                operacion = temperatura.getText().toString();
            else if(longitud.isChecked())
                operacion = longitud.getText().toString();

            input = text.getText().toString();

            //if(!left.isEmpty() && !right.isEmpty() && !input.isEmpty() && !numberText.isEmpty() && !operacion.isEmpty()){
                Log.v("messageUnique", operacion);

                    if(operacion.equals("Longitud")) {
                        Log.v("messageUnique", "in switch");
                        if (a.isChecked() && a1.isChecked())
                            editOutput.setText(numberText);
                        else if (a.isChecked() && b1.isChecked()) {
                            editOutput.setText((number * 100) + "");
                            Log.v("messageUnique", (number / 100) + "");
                        } else if (a.isChecked() && c1.isChecked())
                            editOutput.setText((number / 1000) + "");
                        else if (b.isChecked() && a1.isChecked())
                            editOutput.setText((number / 100) + "");
                        else if (b.isChecked() && b1.isChecked())
                            editOutput.setText(numberText);
                        else if (b.isChecked() && c1.isChecked())
                            editOutput.setText((number / 100000) + "");
                        else if (c.isChecked() && a1.isChecked())
                            editOutput.setText((number * 1000) + "");
                        else if (c.isChecked() && b1.isChecked())
                            editOutput.setText((number * 100000) + "");
                        else if (c.isChecked() && c1.isChecked())
                            editOutput.setText(numberText);
                    }

                    else if(operacion.equals("Masa")){
                        if (a.isChecked() && a1.isChecked())
                            editOutput.setText(numberText);
                        else if (a.isChecked() && b1.isChecked()) {
                            editOutput.setText((number * 100) + "");
                            Log.v("messageUnique", (number / 100) + "");
                        } else if (a.isChecked() && c1.isChecked())
                            editOutput.setText((number / 1000) + "");
                        else if (b.isChecked() && a1.isChecked())
                            editOutput.setText((number / 100) + "");
                        else if (b.isChecked() && b1.isChecked())
                            editOutput.setText(numberText);
                        else if (b.isChecked() && c1.isChecked())
                            editOutput.setText((number / 100000) + "");
                        else if (c.isChecked() && a1.isChecked())
                            editOutput.setText((number * 1000) + "");
                        else if (c.isChecked() && b1.isChecked())
                            editOutput.setText((number * 100000) + "");
                        else if (c.isChecked() && c1.isChecked())
                            editOutput.setText(numberText);
                    }



                    else if(operacion.equals("Temperatura")){
                        if (a.isChecked() && a1.isChecked())
                            editOutput.setText(numberText);
                        else if (a.isChecked() && b1.isChecked()) {
                            number*=9;
                            number/=5;
                            number+=32;
                            editOutput.setText(number+"");
                            Log.v("messageUnique", number+"");
                        } else if (a.isChecked() && c1.isChecked())
                            editOutput.setText((number +273.15) + "");
                        else if (b.isChecked() && a1.isChecked()) {
                            number -= 32;
                            number *=5;
                            number/=9;

                            editOutput.setText(number+ "");
                            Log.v("messageUnique", number+"");
                        }
                        else if (b.isChecked() && b1.isChecked())
                            editOutput.setText(numberText);
                        else if (b.isChecked() && c1.isChecked()) {
                            number *= 9;
                            number /=5;
                            number +=32;
                            number+=273.15;
                            editOutput.setText(number + "");
                        }
                        else if (c.isChecked() && a1.isChecked())
                            editOutput.setText((number -273.15) + "");
                        else if (c.isChecked() && b1.isChecked()){
                            number -= 273.15;
                            number -= 32;
                            number *= 5;
                            number /= 9;
                            editOutput.setText(   number+ "");
                        }

                        else if (c.isChecked() && c1.isChecked())
                            editOutput.setText(numberText);
                    }



            //}
        }
    };
}