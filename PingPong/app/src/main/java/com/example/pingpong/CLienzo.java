package com.example.pingpong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class CLienzo extends View implements SensorEventListener {

    private int black = 0;
    private int white = 0;
    private Paint paint, paintText;
    private final float STROKE_WIDTH = 12f;
    Canvas extraCanvas=null;
    Bitmap extraBitmap=null;
    String time , best;
    private int errors = 0;
    private float barX, barY;
    private float barHeight = 1;
    Thread hilo;

    //pelota
    private float ballX;
    private float ballY;
    private float ballSpeedX = 15f;
    private float ballSpeedY = 15f;
    private final float BALL_RADIUS = 20f;
    private boolean gameStarted = false;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float tiltSensitivity = 35f;
    private float currentTilt = 0f;

    private static final float PADDLE_WIDTH = 20f; // Anco de la ploeta
    private static final float MIN_BALL_SPEED = 15f; // Velocidad minima de la pelota
    private static final float MAX_BALL_SPEED = 30f;


    private float targetY; // Destino final para mejorar la fluidez de movimiento
    private float currentY; // Posición actual del movimiento
    private static final float SMOOTHING_FACTOR = 0.15f; // de 0.1 a 0.3 para mejorar la fluidez del movimiento de la barra
    private static final float TILT_THRESHOLD = 0.5f; // Inclinacion minima para que haya desplazamiento
    private boolean usingTouch = false;
    private static final int CONTROL_MODE_TOUCH = 1;
    private static final int CONTROL_MODE_TILT = 2;
    private int currentControlMode = CONTROL_MODE_TOUCH;

    DBSQLite db;


    public CLienzo(Context context) {
        super(context);
        inicializa();
    }

    public CLienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inicializa();
    }

    private void inicializa(){

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        if (accelerometer != null) {
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }

        black = Color.BLACK;
        white = Color.WHITE;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(STROKE_WIDTH);

        paintText = new Paint();
        paintText.setColor(black);
        paintText.setTextSize(100);
        paintText.setTextAlign(Paint.Align.CENTER);
        db = new DBSQLite(this.getContext());
        best = db.getBest();
        time = "00:00:00";



        restartGame();

        hilo = new Thread(){
            public synchronized void run(){
                while(true){
                    try{
                        Thread.sleep(16);

                        if (gameStarted && System.currentTimeMillis() % 1000 < 16){
                            LocalTime timeF = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
                            LocalTime newTime = timeF.plusSeconds(1);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            time = newTime.format(formatter);
                        }

                        if(gameStarted){
                            updateBall();
                        }

                        postInvalidate();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        hilo.start();


        currentY = getHeight() / 2f;
        targetY = currentY;


        tiltSensitivity = 12f; //

    }


    private void updateBall() {

        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Bordes del juego
        float topBoundary = getHeight() / 6f;
        float leftWallWidth = getWidth() / 16f;
        float rightWallWidth = getWidth() - getWidth() / 16f;

        // Bordes de arriba y abajo
        if (ballY - BALL_RADIUS <= topBoundary) {
            ballY = topBoundary + BALL_RADIUS;
            ballSpeedY = Math.abs(ballSpeedY);
        } else if (ballY + BALL_RADIUS >= getHeight()) {
            ballY = getHeight() - BALL_RADIUS;
            ballSpeedY = -Math.abs(ballSpeedY);
        }

        // Choque con la barra
        float paddleLeft = barX;
        float paddleRight = barX * 1.05f;
        float paddleTop = barY;
        float paddleBottom = barY * 1.25f * barHeight;


        // checar si la pelota esta a la altura de la barra
        boolean withinPaddleY = ballY >= paddleTop && ballY <= paddleBottom;
        boolean approachingPaddle = ballSpeedX > 0 &&
                ballX + BALL_RADIUS >= paddleLeft &&
                ballX - BALL_RADIUS <= paddleRight;

        if (withinPaddleY && approachingPaddle) {
            // Choque con la barra
            ballX = paddleLeft - BALL_RADIUS;

            //Angulo de rebote
            float relativeIntersectY = (paddleTop + (paddleBottom - paddleTop) / 2) - ballY;
            float normalizedIntersectY = relativeIntersectY / ((paddleBottom - paddleTop) / 2);
            float bounceAngle = normalizedIntersectY * -(float)Math.PI / 3;

            // Velocidad de l rbeote
            float speed = Math.min(Math.abs(ballSpeedX) * 1.05f, MAX_BALL_SPEED);
            ballSpeedX = -speed * (float)Math.cos(bounceAngle);
            ballSpeedY = speed * (float)Math.sin(bounceAngle);

            // Hacer barra mas pequeña
            barHeight *= 0.99f;
        }

        // Pared izquierda
        if (ballX - BALL_RADIUS <= leftWallWidth) {
            ballX = leftWallWidth + BALL_RADIUS;
            ballSpeedX = Math.abs(ballSpeedX);
        }

        // Pared derecha, un error
        if (ballX + BALL_RADIUS >= rightWallWidth) {
            errors++;
            if (errors >= 3) {
                restartGame();
            } else {
                resetBall();
            }
        }
    }


    private void restartGame() {
        // Guardar el mejor tiempo
        if (gameStarted && time != null && !time.equals("00:00:00")) {
            try {
                LocalTime currentTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));

                LocalTime bestTime = LocalTime.parse("00:" + best,
                        DateTimeFormatter.ofPattern("HH:mm:ss"));


                if (best.equals("00:00") || currentTime.compareTo(bestTime) > 0) {
                    best = time.substring(3, 8);
                    db.saveBest(best);
                }
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                best = "00:00";
            }
        }


        errors = 0;
        time = "00:00:00";
        gameStarted = false;
        barHeight = 1;
        resetBall();
        invalidate();
    }


    private void resetBall() {
        ballX = getWidth() / 2f;
        ballY = getHeight() / 2f;

        float angle = (float)(Math.random() * Math.PI / 3 - Math.PI / 6);
        ballSpeedX = -(float)(Math.cos(angle) * MIN_BALL_SPEED);
        ballSpeedY = (float)(Math.sin(angle) * MIN_BALL_SPEED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(extraBitmap,0,0,null);

        //scoreboard
        paint.setColor(white);
        canvas.drawRect(0, 0, getWidth(), (float) getHeight() /6, paint);

        //tiempo
        paintText.setColor(black);
        paintText.setTextSize(60f);
        canvas.drawText("TIME:", getWidth() / 2, (float) getHeight() /18, paintText);

        paintText.setTextSize(100f);
        if(time.equals(null)){
            time = "00:00:00";
        }
        canvas.drawText(time.substring(3), getWidth() / 2, (float) getHeight() /9, paintText);

        //best
        paintText.setColor(black);
        paintText.setTextSize(60f);
        canvas.drawText("BEST:", (float)getWidth()*5/6, (float) getHeight() /18, paintText);

        paintText.setTextSize(70f);
        canvas.drawText(best, (float)getWidth()*5/6, (float) getHeight()/9, paintText);


        //errors
        paintText.setColor(black);
        paintText.setTextSize(60f);
        canvas.drawText("ERRORS:", (float)getWidth()/6, (float) getHeight() /18, paintText);

        paintText.setTextSize(70f);
        canvas.drawText(errors+"", (float)getWidth()/6, (float) getHeight()/9, paintText);

        //wall
        paint.setColor(white);
        canvas.drawRect(0, (float)getHeight()/6, getWidth()/16, getHeight(), paint);
        canvas.drawRect(getWidth()-getWidth()/16, (float)getHeight()/6, getWidth(), getHeight(), paint);

        //slide
        canvas.drawRect(barX, barY, barX*1.05f, (barY*1.25f)*barHeight,  paint);

        //ball
        paint.setColor(white);
        canvas.drawCircle(ballX, ballY, BALL_RADIUS, paint);


        paint.setColor(white);
        canvas.drawRect(barX, barY, barX * 1.05f, (barY * 1.25f) * barHeight, paint);

    }

    private void reset(){

        if(extraBitmap!=null){
            extraBitmap.recycle();
        }
        extraBitmap=Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        extraCanvas=new Canvas(extraBitmap);
        extraCanvas.drawColor(black);
        resetBall();
        invalidate();
        Toast.makeText(this.getContext(), "Cleared", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        barX = getWidth() * 13 / 16;
        currentY = getHeight() / 2;
        targetY = currentY;
        barY = currentY;
        resetBall();
        reset();
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentControlMode = CONTROL_MODE_TOUCH;
        usingTouch = true;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                targetY = event.getY();
                if (!gameStarted) {
                    gameStarted = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                targetY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                usingTouch = false;
                break;
        }


        float minY = getHeight() / 6f;
        float maxY = getHeight() - (barY * 0.25f) * barHeight;
        targetY = Math.max(minY, Math.min(targetY, maxY));

        updatePaddlePosition();
        invalidate();
        return true;
    }



        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && !usingTouch) {
                currentControlMode = CONTROL_MODE_TILT;


                float tiltY = event.values[1]; // y
                float tiltZ = event.values[2]; // x


                float tiltValue = Math.abs(tiltY) > Math.abs(tiltZ) ? tiltY : tiltZ;


                if (Math.abs(tiltValue) > TILT_THRESHOLD) {
                    if (gameStarted) {

                        float movement = -tiltValue * tiltSensitivity;
                        targetY = currentY + movement;


                        float minY = getHeight() / 6f;
                        float maxY = getHeight() - (barY * 0.25f) * barHeight;
                        targetY = Math.max(minY, Math.min(targetY, maxY));

                        updatePaddlePosition();
                        postInvalidate();
                    }
                }
            }
        }

    private void updatePaddlePosition() {

        currentY = currentY + (targetY - currentY) * SMOOTHING_FACTOR;
        barY = currentY;

        // asegurar que se quede dentro de los limites la barra
        float minY = getHeight() / 6f;
        float maxY = getHeight() - (barY * 0.25f) * barHeight;
        barY = Math.max(minY, Math.min(barY, maxY));
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}
