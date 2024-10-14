package com.example.practica12_controlespersonalizados;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class CustomButton extends AppCompatButton {

    float escala = getResources().getDisplayMetrics().density;
    Paint pFondo = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pFondoPress = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pTexto = new Paint(Paint.ANTI_ALIAS_FLAG);
    private LinearGradient gradient;
    private int width;
    private int height;

    public CustomButton(@NonNull Context context) {
        super(context);
        initialize();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        // Setup the paint for the text
        pTexto.setColor(Color.WHITE);
        pTexto.setTextSize(50f);
        pTexto.setFakeBoldText(true);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Get the button's dimensions
        width = getWidth();
        height = getHeight();

        gradient = new LinearGradient(0, 0, width, height,
                Color.parseColor("#ff0000"), // Orange color
                Color.parseColor("#0000FF"), // Blue color
                Shader.TileMode.CLAMP);
        pFondo.setShader(gradient);


        gradient = new LinearGradient(0, 0, width, height,
                Color.parseColor("#0000FF"), // Orange color
                Color.parseColor("#ff0000"), // Blue color
                Shader.TileMode.CLAMP);
        pFondoPress.setShader(gradient);

        RectF rect = new RectF(0, 0, width, height);

        // Check if the button is pressed and draw the appropriate gradient
        if (this.isPressed()) {

            canvas.drawRoundRect(rect, 30f, 30f, pFondoPress);
        } else {

            canvas.drawRoundRect(rect, 30f, 30f, pFondo);
        }

        // Draw the button text
        float textWidth = pTexto.measureText(getText().toString());
        float textX = (getWidth() - textWidth) / 2; // Center the text horizontally
        float textY = (getHeight() + pTexto.getTextSize()) / 2 - 10; // Center the text vertically
        canvas.drawText(getText().toString(), textX, textY, pTexto);
    }
}
