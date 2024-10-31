package com.example.practica18sensores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Board extends View {

    int backgroundColor = Color.WHITE;
    int circleColor = Color.BLUE;

    float x, y;

    float width, height;

    Canvas extraCanvas = null;
    Bitmap extraBitmap = null;

    Paint circle = new Paint();


    public Board(Context context) {
        super(context);
        circle.setColor(circleColor);
        circle.setStyle(Paint.Style.FILL);
        x = width/2;
        y = height/2;

    }

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        circle.setColor(circleColor);
        circle.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, 50, circle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        x = width/2;
        y = height/2;

        // Initialize bitmap and canvas to match new size
        extraBitmap = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        extraCanvas = new Canvas(extraBitmap);
        extraCanvas.drawColor(backgroundColor);
    }

    public void sensorMovement(double Ax, double Ay, double Az, double Gx, double Gy, double Gz){


        x += Ax;
        y += Ay;
        if(x > width)
            x = 0;
        if(x < 0)
            x = width;
        if(y > height)
            y = 0;
        if(y < 0)
            y = height;

        invalidate();

    }
}
