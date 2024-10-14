package com.example.practica51;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    //calculadora
    Button ac,dot, equal, plusMinus, module, division, times, minus, plus,
            zero, one, two, three, four, five, six, seven, eight, nine;
    TextView result;

    ArrayList<String> number1 = new ArrayList<>();
    ArrayList<Character> number2 = new ArrayList<>();
    boolean oneOrTwo = false;
    Stack<String> operators = new Stack<>();
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

        ac = findViewById(R.id.ac);
        ac.setOnClickListener(text);

        dot.findViewById(R.id.dot);
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

    private View.OnClickListener text = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button pressed = (Button) view;
            String which = pressed.getText().toString();

            if(which == "AC"){
                number1.clear();
                number2.clear();
                resultNumber=0;
                resultNumber2=0;
                oneOrTwo=false;
                result.setText("0");
            }else if(which == "1" || which == "2" || which == "3" || which == "4" || which == "5" || which == "6" || which == "7" || which == "8" || which == "9" || which == "0") {
                number1.add(which);
                result.setText(result.getText().toString()+""+pressed.getText().toString());
                System.out.println(pressed.getText().toString());
            }else{
                //an operator

                //this is the first operator
                if(!oneOrTwo && which!="=" && number1.size()>0 ){
                    //transform the array to a number
                    StringBuilder numberString = new StringBuilder();
                    for (String ch : number1) {
                        numberString.append(ch);
                    }

                    number1.clear();


                    operators.push(which);

                    // Convert the String to a number
                    resultNumber = Double.parseDouble(numberString.toString());

                    // Output the result
                    System.out.println("The number is: " + resultNumber);
                    oneOrTwo=true;


                }else if(number1.size()>0){ //this is not the first number, there is a number on resultNumber

                    StringBuilder numberString = new StringBuilder();
                    for (String ch : number1) {
                        numberString.append(ch);
                    }

                    number1.clear();
                    resultNumber2 = Double.parseDouble(numberString.toString());

                    if(which=="="){
                        switchOperator(operators.pop());
                    }else
                        switchOperator(which);

                    result.setText(resultNumber+"");
                }

            }


        }

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

    };
}