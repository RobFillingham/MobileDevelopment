package com.example.practica20_rotaciontraslacionscale;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;

public class CLienzo extends View
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private final int SIZE = 60;
    private HashMap<Integer, PointF> punterosActivos = new HashMap<>();
    private Paint myPaint;
    private int[] colors = {
            Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY,
            Color.RED, Color.DKGRAY, Color.LTGRAY,
            Color.YELLOW
    };
    private Paint textPaint = new Paint();
    private String txtGesture;
    private GestureDetector mDetector;
    private ScaleGestureDetector scaleDetector;
    private float scaleFactor = 1.0f;
    private float rotationAngle = 0.0f;
    private PointF translateOffset = new PointF(0, 0);
    private PointF lastTouch = new PointF();
    private boolean isTranslating = false;

    public CLienzo(Context context) {
        super(context);
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40f);
        txtGesture = "gesture actual";

        mDetector = new GestureDetector(context.getApplicationContext(), this);
        mDetector.setOnDoubleTapListener(this);

        scaleDetector = new ScaleGestureDetector(context.getApplicationContext(),
                new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        scaleFactor *= detector.getScaleFactor();
                        scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
                        invalidate();
                        return true;
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        mDetector.onTouchEvent(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastTouch.set(event.getX(), event.getY());
                isTranslating = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isTranslating) {
                    float dx = event.getX() - lastTouch.x;
                    float dy = event.getY() - lastTouch.y;
                    translateOffset.x += dx;
                    translateOffset.y += dy;
                    lastTouch.set(event.getX(), event.getY());
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isTranslating = false;
                break;
        }

        int indice = event.getActionIndex();
        int apuntadorId = event.getPointerId(indice);
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN ||
                event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            PointF f = new PointF();
            f.x = event.getX(indice);
            f.y = event.getY(indice);
            punterosActivos.put(apuntadorId, f);
        }

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int size = event.getPointerCount();
            for (int i = 0; i < size; i++) {
                PointF puntero = punterosActivos.get(event.getPointerId(i));
                if (puntero != null) {
                    puntero.x = event.getX(i);
                    puntero.y = event.getY(i);
                }
            }
        }

        if (event.getActionMasked() == MotionEvent.ACTION_UP ||
                event.getActionMasked() == MotionEvent.ACTION_POINTER_UP ||
                event.getActionMasked() == MotionEvent.ACTION_CANCEL) {
            punterosActivos.remove(apuntadorId);
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        // Aplicar transformación: translate, scale y rotate
        canvas.translate(translateOffset.x, translateOffset.y);
        canvas.scale(scaleFactor, scaleFactor, getWidth() / 2f, getHeight() / 2f);
        canvas.rotate(rotationAngle, getWidth() / 2f, getHeight() / 2f);

        // Dibuja un círculo en el centro del canvas para visualizar la rotación
        myPaint.setColor(Color.RED); // Cambia el color para que sea visible
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, SIZE, myPaint);

        int size = punterosActivos.size();
        Iterator<Integer> itr = punterosActivos.keySet().iterator();
        while (itr.hasNext()) {
            int i = itr.next();
            PointF puntero = punterosActivos.get(i);
            if (puntero != null) {
                myPaint.setColor(colors[i % colors.length]);
                canvas.drawCircle(puntero.x, puntero.y, SIZE, myPaint);
            }
        }

        canvas.restore();
        canvas.drawText("Total punteros: " + punterosActivos.size(),
                10f, 40f, textPaint);
        canvas.drawText("Gesture: ", 10f, 80f, textPaint);
        canvas.drawText(txtGesture, 10f, 120f, textPaint);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        txtGesture = "onSingleTapConfirmed ";
        txtGesture += motionEvent.toString();
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        txtGesture = "onDoubleTap ";
        txtGesture += motionEvent.toString();
        rotationAngle += 15.0f; // Rotación al hacer doble toque
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        txtGesture = "onDoubleTapEvent ";
        txtGesture += motionEvent.toString();
        invalidate();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        txtGesture = "onDown ";
        txtGesture += motionEvent.toString();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        txtGesture = "onShowPress ";
        txtGesture += motionEvent.toString();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        txtGesture = "onSingleTapUp ";
        txtGesture += motionEvent.toString();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        txtGesture = "onScroll ";
        txtGesture += motionEvent.toString();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        txtGesture = "onLongPress ";
        txtGesture += motionEvent.toString();
        invalidate();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        txtGesture = "onFling ";
        txtGesture += motionEvent.toString();
        return true;
    }
}
