package com.example.practica19multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CLienzo extends View implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    private final int SIZE = 60;
    private HashMap<Integer, PointF> punterosActivos = new HashMap<Integer, PointF>();
    private Paint myPaint = new Paint();
    private int[] colors = {
            Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW, Color.CYAN,
            Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.BLACK
    };
    private Paint textPaint = new Paint();
    private String txtGesture;
    private GestureDetector mDetector;

    private boolean[] activeGestures = {false,false,false,false,false,false,false,false,false};
    private boolean[] gestureOnBoard = {false,false,false,false,false,false,false,false,false};

    public CLienzo(Context context) {
        super(context);
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40f);


        txtGesture="gesture actual";
        mDetector = new GestureDetector(context.getApplicationContext(), this);
        mDetector.setOnDoubleTapListener(this);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int indice = event.getActionIndex();
        int apuntadorId = event.getPointerId(indice);

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
            PointF f = new PointF();
            f.x = event.getX(indice);
            f.y = event.getY(indice);
        }
        invalidate();


         /*
        This code checks if the GestureDetector (mDetector) successfully recognized a gesture event,
        allowing it to handle gestures like taps, flings, and other
        predefined actions.
         */

        if(mDetector.onTouchEvent(event)){
            return true;
        }else{
            return super.onTouchEvent(event);
        }


    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int size = punterosActivos.size();
        int i = 0;
        PointF puntero = new PointF();
        Iterator<Integer> itr = punterosActivos.keySet().iterator();



        while (itr.hasNext()){
            i = itr.next();
            puntero = punterosActivos.get(i);
            if (puntero != null) {
                myPaint.setColor(colors[i% colors.length]);
            }
            canvas.drawCircle(puntero.x, puntero.y, SIZE, myPaint);
            //i++;
        }



        if(activeGestures[0] && !gestureOnBoard[0]){
            txtGesture += "Single Tap Confirmed; ";
            gestureOnBoard[0]=true;
        }
        if(activeGestures[1] && !gestureOnBoard[1]){
            txtGesture += "Double Tap; ";
            gestureOnBoard[1]=true;
        }
        if(activeGestures[2] && !gestureOnBoard[2]){
            txtGesture += "Down; ";
            gestureOnBoard[2]=true;
        }
        if(activeGestures[3] && !gestureOnBoard[3]){
            txtGesture += "Show Press; ";
            gestureOnBoard[3]=true;
        }
        if(activeGestures[4] && !gestureOnBoard[4]){
            txtGesture += "Single Tap Up; ";
            gestureOnBoard[4]=true;
        }
        if(activeGestures[5] && !gestureOnBoard[5]){
            txtGesture += "Scroll; ";
            gestureOnBoard[5]=true;


        }
        if(activeGestures[6] && !gestureOnBoard[6]){
            txtGesture += "Long Press; ";
            gestureOnBoard[6]=true;
        }
        if(activeGestures[7] && !gestureOnBoard[7]){
            txtGesture += "Fling; ";
            gestureOnBoard[7]=true;
        }



        canvas.drawText("Total punteros: " + punterosActivos.size(), 10f, 40f, textPaint);
        canvas.drawText("Gesture: ", 10f, 120f, textPaint);
        canvas.drawText(txtGesture, 10f, 160f, textPaint);


    }


    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {

        activeGestures[0]=true;
        resetGestureAfterDelay(0);
        return true;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        activeGestures[1]=true;
        resetGestureAfterDelay(1);

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        activeGestures[1]=true;
        resetGestureAfterDelay(1);


        return true;
    }
    int points=0;
    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        activeGestures[2]=true;
        resetGestureAfterDelay(2);

        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {
        activeGestures[3]=true;
        resetGestureAfterDelay(3);
        invalidate();

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {

        activeGestures[4]=true;
        resetGestureAfterDelay(4);
        return true;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {


        activeGestures[5]=true;
        resetGestureAfterDelay(5);
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {


        activeGestures[6]=true;
        resetGestureAfterDelay(6);
        invalidate();
    }

    @Override
    public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {


        activeGestures[7]=true;
        resetGestureAfterDelay(7);
        return true;
    }

    private void resetGestureAfterDelay(int i) {
        new android.os.Handler().postDelayed(() -> {

            activeGestures[i]=false;
            gestureOnBoard[i]=false;
            txtGesture="";

            postInvalidate();  // Force re-draw to reflect change
        }, 4000); // Adjust delay as needed
    }
}
