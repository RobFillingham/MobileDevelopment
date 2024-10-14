package com.example.actividad14_controlespersonalizados14;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SelectColor extends View {

    private int color=100;

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
}
