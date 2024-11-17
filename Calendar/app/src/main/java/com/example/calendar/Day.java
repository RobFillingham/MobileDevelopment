package com.example.calendar;




import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Day extends View {
    private static final float CORNER_RADIUS = 12f;
    private static final float TEXT_SIZE_DP = 14f; // Smaller text size
    private static final float PADDING_DP = 4f;

    private final Paint paintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF backgroundRect = new RectF();

    private int textColor;
    private int backgroundColor;
    private int day;
    private float density;
    private boolean isSelected;
    private boolean isToday;

    public Day(Context context) {
        super(context);
        init(context);
    }

    public Day(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        density = context.getResources().getDisplayMetrics().density;


        textColor = Color.WHITE;
        backgroundColor = Color.GRAY;


        paintBackground.setColor(backgroundColor);


        paintText.setColor(textColor);
        paintText.setTextSize(TEXT_SIZE_DP * density);
        paintText.setTextAlign(Paint.Align.CENTER);


        setClickable(true);


        day = 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int width = MeasureSpec.getSize(widthMeasureSpec);
        int desiredSize = (int) (36 * density); // 36dp default size


        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            desiredSize = width;
        }

        setMeasuredDimension(desiredSize, desiredSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int size = Math.min(getWidth(), getHeight());
        float padding = PADDING_DP * density;


        backgroundRect.set(padding, padding, size - padding, size - padding);
        canvas.drawRoundRect(backgroundRect, CORNER_RADIUS * density, CORNER_RADIUS * density, paintBackground);


        float textY = (size + paintText.getTextSize() - paintText.descent()) / 2;
        canvas.drawText(String.valueOf(day), size / 2f, textY, paintText);
    }


    public void setDay(int day) {
        this.day = day;
        invalidate();
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        paintBackground.setColor(selected ? Color.RED : backgroundColor);
        invalidate();
    }

    public void setIsToday(boolean today) {
        isToday = today;
        if (today) {
            paintBackground.setColor(Color.GREEN);
        } else {
            paintBackground.setColor(backgroundColor);
        }
        invalidate();
    }

    public void setColors(int textColor, int backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        paintText.setColor(textColor);
        paintBackground.setColor(backgroundColor);
        invalidate();
    }


    public int getDay() {
        return day;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isToday() {
        return isToday;
    }
}
