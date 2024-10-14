package com.example.practica5_controles;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    Button btnSumar;
    Button btnRestar;
    EditText txtN1, txtN2;
    TextView txtResult;

    //calculadora
    Button ac,dot, equal, plusMinus, module, division, times, minus, plus,
           zero, one, two, three, four, five, six, seven, eight, nine;
    TextView result;

    ArrayList<String> number1 = new ArrayList<>();
    ArrayList<Character> number2 = new ArrayList<>();
    boolean oneOrTwo = false ;
    Queue<String> operators = new LinkedList<>();
    double resultNumber=0, resultNumber2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       /* btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        txtN1 = findViewById(R.id.txt1);
        txtN2 = findViewById(R.id.txt2);
        txtResult = findViewById(R.id.txtResult);
        btnSumar.setOnClickListener(evento);
        btnRestar.setOnClickListener(eventoRestar);*/

        //calculator
        ac = findViewById(R.id.ac);
        ac.setOnClickListener(text);

        dot = findViewById(R.id.dot);
        dot.setOnClickListener(text);

        equal = findViewById(R.id.equal);
        equal.setOnClickListener(text);

        plusMinus = findViewById(R.id.plusMinus);
        plusMinus.setOnClickListener(text);

        module = findViewById(R.id.module);
        module.setOnClickListener(text);

        division = findViewById(R.id.division);
        division.setOnClickListener(text);

        times = findViewById(R.id.times);
        times.setOnClickListener(text);

        minus = findViewById(R.id.minus);
        minus.setOnClickListener(text);

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(text);

        zero = findViewById(R.id.zero);
        zero.setOnClickListener(text);

        one = findViewById(R.id.one);
        one.setOnClickListener(text);

        two = findViewById(R.id.two);
        two.setOnClickListener(text);

        three = findViewById(R.id.three);
        three.setOnClickListener(text);

        four = findViewById(R.id.four);
        four.setOnClickListener(text);

        five = findViewById(R.id.five);
        five.setOnClickListener(text);

        six = findViewById(R.id.six);
        six.setOnClickListener(text);

        seven = findViewById(R.id.seven);
        seven.setOnClickListener(text);

        eight = findViewById(R.id.eight);
        eight.setOnClickListener(text);

        nine = findViewById(R.id.nine);
        nine.setOnClickListener(text);


        result = findViewById(R.id.text);

    }

    private View.OnClickListener evento = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(v == btnSumar){
                int n1 = Integer.parseInt(txtN1.getText().toString());
                int n2 = Integer.parseInt(txtN2.getText().toString());
                txtResult.setText(n1+n2+"");
            }
        }
    };

    private View.OnClickListener eventoRestar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int n1 = Integer.parseInt(txtN1.getText().toString());
            int n2 = Integer.parseInt(txtN2.getText().toString());
            txtResult.setText(n1-n2+"");
        }
    };

    private View.OnClickListener text = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button pressed = (Button) view;
            String which = pressed.getText().toString();
            Log.d("Calculator", which);

            if(which.equals("AC")){
                number1.clear();
                number2.clear();
                resultNumber=0;
                resultNumber2=0;
                oneOrTwo=false;
                result.setText("0");
            }else if(which.equals("1") || which.equals("2") || which.equals("3") || which.equals("4") || which.equals("5") || which.equals("6") || which.equals("7") || which.equals("8") || which.equals("9") || which.equals("0")) {
                number1.add(which);
                if(result.getText().toString().equals("0"))
                    result.setText(pressed.getText().toString());
                else
                    result.setText(result.getText().toString()+""+pressed.getText().toString());
                System.out.println(pressed.getText().toString());
                Log.d("Calculator","Button pressed");
            }else if(which.equals("=")){
                result.setText("");
                StringBuilder numberString = new StringBuilder();
                for (String ch : number1) {
                    numberString.append(ch);
                }

                number1.clear();




                // Convert the String to a number
                resultNumber2 = Double.parseDouble(numberString.toString());
                switchOperator(operators.peek());
                operators.poll();

                result.setText(resultNumber+"");
            }else{
                //an operator
                result.setText("");
                //this is the first operator
                if(!oneOrTwo && number1.size()>0  ){


                    //transform the array to a number
                    StringBuilder numberString = new StringBuilder();
                    for (String ch : number1) {
                        numberString.append(ch);
                    }

                    number1.clear();


                    operators.add(which);

                    // Convert the String to a number
                    resultNumber = Double.parseDouble(numberString.toString());

                    // Output the result
                    System.out.println("The number is: " + resultNumber);
                    oneOrTwo=true;

                    result.setText("0");


                }else  //this is not the first number, there is a number on resultNumber
                    if(number1.size()>0) {
                        StringBuilder numberString = new StringBuilder();
                        for (String ch : number1) {
                            numberString.append(ch);
                        }

                        number1.clear();
                        resultNumber2 = Double.parseDouble(numberString.toString());


                        operators.add(which);


                        switchOperator(operators.peek());
                        operators.poll();

                        result.setText(resultNumber + "");
                    } else{
                        operators.add(which);
                    }


            }


        }

    };

    private void switchOperator(String operator){
        switch(operator){

            // Convert the String to a number
            case "%":
                resultNumber%=resultNumber2;
                break;
            case "/":
                resultNumber/=resultNumber2;
                break;
            case "X":
                resultNumber*=resultNumber2;
                break;
            case "-":
                resultNumber-=resultNumber2;
                break;
            case "+":
                resultNumber+=resultNumber2;
                break;
            case "=":

        }

    }










}