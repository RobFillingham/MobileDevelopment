package com.example.practica12_controlespersonalizados;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    float escala = getResources().getDisplayMetrics().density;
    Paint pFondo = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pTexto = new Paint(Paint.ANTI_ALIAS_FLAG);


    public CustomEditText(@NonNull Context context) {
        super(context);
        inicializa();
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializa();
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializa();
    }

    private void inicializa() {
        pFondo.setColor(Color.BLACK);
        pFondo.setStyle(Paint.Style.FILL);
        pTexto.setColor(Color.WHITE);
        pTexto.setTextSize(30f);
        pTexto.setFakeBoldText(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(getWidth() - 30 * escala, 5 * escala, getWidth() - 5 * escala, 25 * escala);
        canvas.drawRoundRect(rect, 10f, 10f, pFondo);
        canvas.drawText("" + getText().toString().length(), getWidth() - 24 * escala, 19 * escala, pTexto);
    }
}
