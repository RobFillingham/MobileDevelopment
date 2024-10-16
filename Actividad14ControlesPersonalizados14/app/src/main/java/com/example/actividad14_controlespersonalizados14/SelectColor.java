package com.example.actividad14_controlespersonalizados14;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SelectColor extends View {

    private int color=100, color1=100, color2=100;
    private OnSelectedColorListener listener = null;

    public SelectColor(Context context) {
        super(context);
    }

    public SelectColor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SelectColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnColorListener(OnSelectedColorListener listener){
        this.listener=listener;
    }


    //definimos el alto y largo del view
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int ancho = calcularAncho(widthMeasureSpec);
        int alto = calcularAlto(heightMeasureSpec);
        setMeasuredDimension(ancho, alto);
    }

    private int calcularAncho(int widthMeasureSpec){
        int res=100;
        int modo=MeasureSpec.getMode(widthMeasureSpec);
        int limite=MeasureSpec.getSize(widthMeasureSpec);
        if(modo==MeasureSpec.AT_MOST || modo==MeasureSpec.EXACTLY){
            res=limite;
        }
        return res;
    }

    private int calcularAlto(int heightMeasureSpec){
        int res=100;
        int modo=MeasureSpec.getMode(heightMeasureSpec);
        int limite=MeasureSpec.getSize(heightMeasureSpec);
        if(modo==MeasureSpec.AT_MOST || modo==MeasureSpec.EXACTLY){
            res=limite;
        }
        return res;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        float alto = getMeasuredHeight();
        float ancho = getMeasuredWidth();


        Paint pRelleno = new Paint();
        pRelleno.setStyle(Paint.Style.FILL);

        //Blanco
        pRelleno.setColor(Color.RED);
        canvas.drawRect(0, 0, ancho/3f, alto/2f, pRelleno);

        //Negro
        pRelleno.setColor(Color.GREEN);
        canvas.drawRect(ancho/3f, 0f, 2*ancho/3, alto/2f, pRelleno);

        //Azul
        pRelleno.setColor(Color.BLUE);
        canvas.drawRect(2*ancho/3f, 0f, ancho, alto/2f, pRelleno);

        //Color Seleccionado
        pRelleno.setColor(Color.argb(255, color, color1, color2));
        canvas.drawRect(0, alto/2f, ancho, alto, pRelleno);

        //Marco del control
        Paint pBorde = new Paint();
        pRelleno.setStyle(Paint.Style.STROKE);
        pRelleno.setColor(Color.BLACK);
        canvas.drawRect(0, 0, ancho-1, alto/2, pRelleno);
        canvas.drawRect(0, 0, ancho-1, alto-1, pRelleno);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getY()>0 && event.getY()<getMeasuredHeight()/2){
            if(event.getX()>0 && event.getX()<getMeasuredWidth()) {
                if(event.getX()<getMeasuredWidth()/3) {
                    //red
                    color += 5;
                    if (color > 255)
                        color = 0;
                }else if(event.getX()>getMeasuredWidth()/3 && event.getX()<2*getMeasuredWidth()/3){
                    //green
                    color1 += 5;
                    if (color1 > 255) {
                        color1 = 255;
                    }
                }else{
                    //blue
                    color2 += 5;
                    if (color2 > 255) {
                        color2 = 255;
                    }
                }
                this.invalidate();
            }
        }else if(event.getY()>getMeasuredHeight()/2 && event.getY()<getMeasuredHeight()){
            color1=0;
            color2=0;
            color=0;
            this.invalidate();
            listener.onSelectedColor(color);
        }
        return super.onTouchEvent(event);
    }



}
