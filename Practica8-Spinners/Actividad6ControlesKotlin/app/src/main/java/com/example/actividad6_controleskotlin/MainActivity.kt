package com.example.actividad6_controleskotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Integer.parseInt
import java.util.LinkedList
import java.util.Queue

class MainActivity : AppCompatActivity() {

    /*lateinit var  btnSumar : Button
    lateinit var txtResult : TextView
    lateinit var txtN1:EditText
    lateinit var txtN2:EditText*/

    //calculator
    lateinit var ac: Button
    lateinit var dot: Button
    lateinit var equal: Button
    lateinit var plusMinus: Button
    lateinit var module: Button
    lateinit var division: Button
    lateinit var times: Button
    lateinit var minus: Button
    lateinit var plus: Button
    lateinit var zero: Button
    lateinit var one: Button
    lateinit var two: Button
    lateinit var three: Button
    lateinit var four: Button
    lateinit var five: Button
    lateinit var six: Button
    lateinit var seven: Button
    lateinit var eight: Button
    lateinit var nine: Button
    lateinit var result: TextView

    val number1 = ArrayList<String>()
    val number2 = ArrayList<Char>()
    var oneOrTwo = false
    val operators: Queue<String> = LinkedList()
    var resultNumber = 0.0
    var resultNumber2 = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*btnSumar  = findViewById(R.id.btnSumar)
        txtResult  = findViewById(R.id.txtResult)
        txtN1 = findViewById(R.id.txtN1)
        txtN2= findViewById(R.id.txtN2)

        btnSumar.setOnClickListener(evento)*/

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

    private val text = View.OnClickListener { view ->
        val pressed = view as Button
        val which = pressed.text.toString()
        Log.d("Calculator", which)

        when {
            which == "AC" -> {
                number1.clear()
                number2.clear()
                resultNumber = 0.0
                resultNumber2 = 0.0
                oneOrTwo = false
                result.text = "0"
            }
            which in listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0") -> {
                number1.add(which)
                if (result.text.toString() == "0") {
                    result.text = pressed.text.toString()
                } else {
                    result.text = result.text.toString() + pressed.text.toString()
                }
                println(pressed.text.toString())
                Log.d("Calculator", "Button pressed")
            }
            which == "=" -> {
                result.text = ""
                val numberString = StringBuilder()
                for (ch in number1) {
                    numberString.append(ch)
                }

                number1.clear()

                // Convert the String to a number
                resultNumber2 = numberString.toString().toDouble()
                switchOperator(operators.peek())
                operators.poll()

                result.text = resultNumber.toString()
            }
            else -> {
                // an operator
                result.text = ""
                if (!oneOrTwo && number1.isNotEmpty()) {
                    // transform the array to a number
                    val numberString = StringBuilder()
                    for (ch in number1) {
                        numberString.append(ch)
                    }

                    number1.clear()

                    operators.add(which)

                    // Convert the String to a number
                    resultNumber = numberString.toString().toDouble()

                    // Output the result
                    println("The number is: $resultNumber")
                    oneOrTwo = true

                    result.text = "0"
                } else if (number1.isNotEmpty()) {
                    val numberString = StringBuilder()
                    for (ch in number1) {
                        numberString.append(ch)
                    }

                    number1.clear()
                    resultNumber2 = numberString.toString().toDouble()

                    operators.add(which)

                    switchOperator(operators.peek())
                    operators.poll()

                    result.text = resultNumber.toString()
                } else {
                    operators.add(which)
                }
            }
        }
    }

    private fun switchOperator(operator: String?) {
        when (operator) {
            "%" -> resultNumber %= resultNumber2
            "/" -> resultNumber /= resultNumber2
            "X" -> resultNumber *= resultNumber2
            "-" -> resultNumber -= resultNumber2
            "+" -> resultNumber += resultNumber2
        }
    }


    /*private val evento = View.OnClickListener { v ->
        if(v === btnSumar){
            val n1 = txtN1.text.toString().toInt()
            val n2 = txtN2.text.toString().toInt()
            txtResult.setText((n1+n2).toString()+"")
        }
    }*/


}