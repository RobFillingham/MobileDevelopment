package com.example.practica17_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;


public class CLienzo extends View {
    Drawable imagen;
    public CLienzo(Context context){
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setBackgroundResource(R.drawable.degradado);

        imagen = AppCompatResources.getDrawable(getContext(), R.drawable.baseline_favorite_24);
        imagen.setBounds(w/10, h/10, 1000, 1000);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        imagen.draw(canvas);
    }


}
