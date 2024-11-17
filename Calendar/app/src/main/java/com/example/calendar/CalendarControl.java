package com.example.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarControl extends LinearLayout {

    Day daySelected;
    DateListener listener;

    public CalendarControl(Context context) {
        super(context);
        inicializar();
    }

    public CalendarControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializar();

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarControl);
        String dia = a.getString(R.styleable.CalendarControl_day);
        String mes = a.getString(R.styleable.CalendarControl_month);
        String anio = a.getString(R.styleable.CalendarControl_year);



        if(dia != null){
            setDay(Integer.parseInt(dia));
        }
        if(mes != null) {
            setMonth(Integer.parseInt(mes));
        }
        if(anio != null) {
            setYear(Integer.parseInt(anio));
        }

        a.recycle();
    }

    public CalendarControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar();
    }

    private void inicializar(){

        LayoutInflater li = LayoutInflater.from(getContext());
        li.inflate(R.layout.calendario, this, true);


        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, months);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner monthSpinner = findViewById(R.id.month);
        monthSpinner.setAdapter(adaptador);
        monthSpinner.setSelection(2);

        String[] years = {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, years);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner yearSpinner = findViewById(R.id.year);
        yearSpinner.setAdapter(adaptador2);
        yearSpinner.setSelection(0);


        fillGrid();

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillGrid();
                newDate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillGrid();
                newDate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void fillGrid() {
        GridLayout grid = findViewById(R.id.grid);
        //grid.removeAllViews(); // Clear existing views

        for (int i = grid.getChildCount() - 1; i >= 0; i--) {
            View child = grid.getChildAt(i);
            if(child.getId() != R.id.weekOne && child.getId() != R.id.weekTwo && child.getId() != R.id.weekThree && child.getId() != R.id.weekFour
                    && child.getId() != R.id.weekFive && child.getId() != R.id.weekSix && child.getId() != R.id.wk && child.getId() != R.id.sunday
                    && child.getId() != R.id.monday && child.getId() != R.id.tuesday && child.getId() != R.id.wednesday && child.getId() != R.id.thursday
                    && child.getId() != R.id.friday && child.getId() != R.id.saturday) {
                grid.removeView(child);
            }
        }

        // Primer dia del mes
        LocalDate date = LocalDate.of(getSelectedYear(), getSelectedMonth(), 1);

        // Numero de días en el mes
        int daysInMonth = date.lengthOfMonth();

        // El primer dia de ese mes pero en lunes, martes etc
        int dayOfWeek = date.getDayOfWeek().getValue();

        // El domingo lo pasamos a uno
        int startOffset = dayOfWeek == 7 ? 1 : dayOfWeek + 1;


        int totalCells = 42;

        TextView one, two, three, four, five, six, seven;

        one = findViewById(R.id.weekOne);
        int week = ((getSelectedMonth()-1)*4);
        one.setText(week+"");

        week++;
        two = findViewById(R.id.weekTwo);
        two.setText(week+"");

        week++;
        three = findViewById(R.id.weekThree);
        three.setText(week+"");

        week++;
        four = findViewById(R.id.weekFour);
        four.setText(week+"");

        week++;
        five = findViewById(R.id.weekFive);
        five.setText(week+"");

        week++;
        six = findViewById(R.id.weekSix);
        six.setText(week+"");




        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 8; j++) {
                Day day = new Day(getContext());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);
                params.width = 0;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.setGravity(android.view.Gravity.FILL);

                int margin = (int) (2 * getResources().getDisplayMetrics().density);
                params.setMargins(margin, margin, margin, margin);

                // Numero de día
                int position = ((i - 1) * 7) + j;
                int dayNumber = position - startOffset + 1;

                // Solo los dias que esten en el mes se muestran
                if (dayNumber > 0 && dayNumber <= daysInMonth) {
                    day.setDay(dayNumber);
                    day.setVisibility(View.VISIBLE);

                    // Checar si este día es hoy para ponerlo en verde
                    LocalDate today = LocalDate.now();
                    if (today.getYear() == getSelectedYear() &&
                            today.getMonthValue() == getSelectedMonth() &&
                            today.getDayOfMonth() == dayNumber) {
                        day.setIsToday(true);
                    }
                } else {
                    day.setVisibility(View.INVISIBLE);
                }

                day.setOnClickListener(v -> {
                    Day clickedDay = (Day) v;
                    if (clickedDay.getVisibility() == View.VISIBLE) {
                        clickedDay.setSelected(!clickedDay.isSelected());
                        if (daySelected != null) {
                            daySelected.setSelected(false);
                        }
                        daySelected = clickedDay;
                        onDaySelected(clickedDay.getDay());
                        newDate();
                    }
                });

                grid.addView(day, params);
            }
        }
    }

    public void setDateListener(DateListener listener) {
        this.listener = listener;
    }

    public void setMonth(int month) {
        Spinner monthSpinner = findViewById(R.id.month);
        monthSpinner.setSelection(month - 1);
    }

    public void setYear(int year) {
        Spinner yearSpinner = findViewById(R.id.year);
        for (int i = 0; i < yearSpinner.getCount(); i++) {
            if (yearSpinner.getItemAtPosition(i).equals(String.valueOf(year))) {
                yearSpinner.setSelection(i);
                break;
            }
        }
    }

    public void setDay(int day) {
        GridLayout grid = findViewById(R.id.grid);
        for(int i = 0; i < grid.getChildCount(); i++){
            View child = grid.getChildAt(i);
            if(child instanceof Day){
                Day dayView = (Day) child;
                if(dayView.getDay() == day){
                    dayView.setSelected(true);
                }
            }
        }

        newDate();

    }

    public void newDate(){
        String date = "New Date: ";
        if(daySelected != null){
            date += daySelected.getDay() + "/" + getSelectedMonth() + "/" + getSelectedYear();
        }else{
            date += "01/" +  getSelectedMonth() + "/" + getSelectedYear();
        }
        if(listener != null){
            if(daySelected != null)
                listener.onDateSelected(getSelectedYear(), getSelectedMonth(), daySelected.getDay());
            else
                listener.onDateSelected(getSelectedYear(), getSelectedMonth(), 1);
        }
        Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
    }







    private void onDaySelected(int day) {
        LocalDate selectedDate = LocalDate.of(getSelectedYear(), getSelectedMonth(), day);
        
    }


    private int getSelectedYear() {
        Spinner yearSpinner = findViewById(R.id.year);
        String yearStr = (String) yearSpinner.getSelectedItem();
        return Integer.parseInt(yearStr);
    }

    private int getSelectedMonth() {
        Spinner monthSpinner = findViewById(R.id.month);
        return monthSpinner.getSelectedItemPosition() + 1; // Adding 1 since months are 1-based
    }


}
