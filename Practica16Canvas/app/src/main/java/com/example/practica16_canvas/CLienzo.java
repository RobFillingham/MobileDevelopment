package com.example.practica16_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.graphics.Path;
import android.view.ViewConfiguration;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

public class CLienzo extends View {

    Canvas extraCanvas=null;
    Bitmap extraBitmap=null;

    private final float STROKE_WIDTH = 12f;
    int backgroundColor;
    int drawColor;
    int selectedShape = 0;
    private boolean isDragging = false;
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.auburn);


    Path path=new Path();
    Paint paint=new Paint();
    Paint paintBtn = new Paint();
    Paint paintText = new Paint();
    private Paint dashedPaint = new Paint();


    public CLienzo(Context context){
        super(context);
        inicializa();
    }

    public CLienzo(Context context, AttributeSet attrs){

        super(context, attrs);
        inicializa();
    }

    private void inicializa(){
        //asignamos los colores
        backgroundColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
        drawColor = ResourcesCompat.getColor(getResources(), R.color.white, null);

        //Inicializamos el objeto paint
        paint.setColor(drawColor);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(STROKE_WIDTH);

        // Dashed paint for preview
        dashedPaint.setColor(drawColor);
        dashedPaint.setStyle(Paint.Style.STROKE);
        dashedPaint.setStrokeWidth(STROKE_WIDTH);
        dashedPaint.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0)); // Dotted effect

        //Inicializamos el objeto paintBtn
        paintBtn.setColor(drawColor);
        paintBtn.setStyle(Paint.Style.FILL);

        //Inicializamos el objeto paintText
        paintText.setColor(Color.argb(255, 100, 100, 100));
        paintText.setTextSize(40f);


    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(extraBitmap, 0, 0, null);
        canvas.drawRect(0,0,150,50,paintBtn);
        canvas.drawText("Borrar",10,35,paintText);

        if (isDragging) {
            canvas.drawRect(currentX, currentY, motionTouchEventX, motionTouchEventY, dashedPaint);
        }
    }

    private void reset(){
        if(extraBitmap!=null){
            extraBitmap.recycle();
        }
        extraBitmap=Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        extraCanvas=new Canvas(extraBitmap);
        extraCanvas.drawColor(backgroundColor);
        invalidate();
        Toast.makeText(this.getContext(), "Cleared", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        reset();
    }

    private float motionTouchEventX=0f;
    private float motionTouchEventY=0f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        motionTouchEventX = event.getX();
        motionTouchEventY = event.getY();
        if(motionTouchEventX<200 && motionTouchEventY<200){
            reset();
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchStart();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            touchMove();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            touchUp();
        }
        return true;
    }

    private float currentX=0f, currentY=0f, endindX=0f, endingY=0f;

    public void touchStart(){
        //path.reset();
        //path.moveTo(motionTouchEventX, motionTouchEventY);
        currentX = motionTouchEventX;
        currentY = motionTouchEventY;
        isDragging = true;
    }

    int touchTolerance = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    public void touchMove() {
        /*float dx = Math.abs(motionTouchEventX - currentX);
        float dy = Math.abs(motionTouchEventY - currentY);
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(currentX, currentY,
                    (motionTouchEventX+currentX)/2, (motionTouchEventY+currentY)/2 );
            currentX = motionTouchEventX;
            currentY = motionTouchEventY;
            extraCanvas.drawPath(path, paint);
        }*/
        invalidate();
    }

    public void touchUp() {
        endindX = motionTouchEventX;
        endingY = motionTouchEventY;
        isDragging = false;
        draw();
        //path.reset();
    }

    public void draw(){
        switch (selectedShape){
            case 1:
                extraCanvas.drawPoint(endindX, endingY, paint);
                break;
            case 2:
                extraCanvas.drawLine(currentX, currentY, endindX, endingY, paint);
                break;
            case 3:
                extraCanvas.drawRect(currentX, currentY, endindX, endingY, paint);
                break;
            case 4:
                extraCanvas.drawArc(currentX, currentY, endindX, endingY, 0, 360, true, paint);
                break;
            case 5:
                extraCanvas.drawBitmap(bitmap, currentX, currentY, null);
                break;
            case 6:
                extraCanvas.drawOval(currentX, currentY, endindX, endingY, paint);
                break;
            case 7:
                extraCanvas.drawRect(currentX, currentY, endindX, endingY, paint);
                break;
            case 8:
                extraCanvas.drawText("Hola", endindX, endingY, paint);
                break;
            default:
                extraCanvas.drawPoint(endindX, endingY, paint);
                break;

        }
        invalidate();
    }

    public void setSelectedShape(int shape){
        selectedShape = shape;
    }

}
