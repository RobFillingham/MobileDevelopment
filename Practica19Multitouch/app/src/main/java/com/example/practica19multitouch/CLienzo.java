package com.example.practica19multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class CLienzo extends View {
    private final int SIZE = 60;
    private SparseArray<PointF> punterosActivos = new SparseArray<PointF>();
    private Paint myPaint = new Paint();
    private int[] colors = {
            Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.CYAN,
            Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.BLACK
    };
    private Paint textPaint = new Paint();
    private Thread hilo;

    boolean play = false;

    int time = 10;

    public CLienzo(Context context) {
        super(context);
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30);
        hilo = new Thread(){
            public synchronized void run(){
                while(true){
                    try{
                        Thread.sleep(1000);
                        if(play)
                            time--;
                        if(time == 0){
                            chooseOne();
                            time=10;
                        }
                        invalidate();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        hilo.start();


    }

    private void chooseOne(){
        int size = punterosActivos.size();
        if (size > 0) {
            int random = (int)(Math.random() * size);
            int winningKey = punterosActivos.keyAt(random);
            PointF winningPoint = punterosActivos.get(winningKey);

            punterosActivos.clear();
            if (winningPoint != null) {
                punterosActivos.put(winningKey, winningPoint);
            }
            play = false;
            invalidate();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int indice = event.getActionIndex();
        int apuntadorId = event.getPointerId(indice);

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            play = true;
        }

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            PointF f = new PointF();
            f.x = event.getX(indice);
            f.y = event.getY(indice);
            punterosActivos.put(apuntadorId, f);


        }
        if(event.getActionMasked() == MotionEvent.ACTION_MOVE){
            int size = event.getPointerCount();
            int i=0;
            while(i<size){
                int pointerId = event.getPointerId(i);
                PointF puntero = punterosActivos.get(pointerId);
                if(puntero != null){
                    puntero.x = event.getX(i);
                    puntero.y = event.getY(i);
                }
                i++;
            }
        }
        if(event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP
                || event.getActionMasked() == MotionEvent.ACTION_CANCEL){
            punterosActivos.remove(apuntadorId);
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int size = punterosActivos.size();
        int i = 0;
        PointF puntero = new PointF();
        while (i < size){
            puntero = punterosActivos.valueAt(i);
            if (puntero != null) {
                myPaint.setColor(colors[i% colors.length]);
            }
            canvas.drawCircle(puntero.x, puntero.y, SIZE, myPaint);
            i++;
        }
        canvas.drawText("Tiempo: " + time, 10f, 80f, textPaint);
        canvas.drawText("Punteros: " + punterosActivos.size(), 10f, 120f, textPaint);


    }



}
