package com.example.actividad15_controlpersonalizado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class Galaxy extends AppCompatButton {

    float escala = getResources().getDisplayMetrics().density;
    Paint pFondo = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pTexto = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pDesc = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProgBar = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProgBarTotal = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pProg = new Paint(Paint.ANTI_ALIAS_FLAG);
    private LinearGradient gradient;
    private int width;
    private int height;
    private float progress = 0.8F;

    public Galaxy(@NonNull Context context) {
        super(context);
        initialize();
    }

    public Galaxy(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Galaxy(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }
    private void initialize() {
        // Setup the paint for the text
        pTexto.setColor(Color.WHITE);
        pTexto.setTextSize(80f);
        pTexto.setFakeBoldText(true);

        pDesc.setColor(Color.WHITE);
        pDesc.setTextSize(60f);
        pDesc.setFakeBoldText(true);

        pProg.setColor(Color.WHITE);
        pProg.setTextSize(60f);
        pProg.setFakeBoldText(true);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Agdasima-Regular.ttf");
        pTexto.setTypeface(typeface);

        pDesc.setTypeface(typeface);

        pProg.setTypeface(typeface);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
    public float getProgress() {
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Get the button's dimensions
        width = getWidth();
        height = getHeight();

        /*gradient = new LinearGradient(0, 0, width, height,
                Color.parseColor("#ff0000"), // Orange color
                Color.parseColor("#0000FF"), // Blue color
                Shader.TileMode.CLAMP);*/

        //Black and purple
        gradient = new LinearGradient(0, 0, width*3, height*3,
                Color.parseColor("#121212"), //black
                Color.parseColor("#0000ff"),
                Shader.TileMode.CLAMP);
        pFondo.setShader(gradient);
        RectF rect = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rect, 40f, 40f, pFondo);

        //Progress bar total
        pProgBarTotal.setColor(Color.GRAY);
        RectF rect3 = new RectF(50, getHeight()-125, width-180, height-50);
        canvas.drawRoundRect(rect3, 40f, 40f, pProgBarTotal);

        //Progress bar
        pProgBar.setColor(Color.YELLOW);
        RectF rect2 = new RectF(50, getHeight()-125, (width-180)*progress, height-50);
        canvas.drawRoundRect(rect2, 40f, 40f, pProgBar);




        // Galaxia
        float textWidth = pTexto.measureText(getText().toString());
        float textX = 50; // Center the text horizontally
        float textY = (getHeight() + pTexto.getTextSize()) / 2 - 150; // Center the text vertically
        canvas.drawText(getText().toString(), textX, textY, pTexto);

        //descripcion
        float textWidth2 = pDesc.measureText("Pasado, presente y futuro en una cafetería");
        float textX2 = 50; // Center the text horizontally
        float textY2 = (getHeight() + pDesc.getTextSize()) / 2 - 40; // Center the text vertically
        canvas.drawText("Pasado, presente y futuro en una cafetería", textX2, textY2, pDesc);

        //progreso
        String progressText = (int) (progress * 100) + "%";
        float textWidth3 = pProg.measureText(progressText);
        float textX3 = width-135; //  horizontally
        float textY3 = height-65; //  vertically
        canvas.drawText(progressText, textX3, textY3, pProg);



    }

}
