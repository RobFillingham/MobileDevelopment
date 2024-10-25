package com.example.practica17_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;


public class CLienzo extends View {
    Drawable imagen;
    public CLienzo(Context context){
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        imagen = getResources().getDrawable(R.drawable.android);
        imagen.setBounds(30, 30, 500, 500);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        imagen.draw(canvas);
    }


}
