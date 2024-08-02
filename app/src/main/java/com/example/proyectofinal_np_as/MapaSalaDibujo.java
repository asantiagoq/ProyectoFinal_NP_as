package com.example.proyectofinal_np_as;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MapaSalaDibujo extends View {
    private Paint paint;
    private Paint textPaint;
    private String galleryName;

    public MapaSalaDibujo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // Dibujar un rectángulo para las paredes de la sala
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(100, 100, viewWidth - 100, viewHeight - 100, paint);
        paint.setStyle(Paint.Style.FILL);

        // Dibujar la línea anaranjada para simbolizar una puerta
        paint.setColor(Color.parseColor("#FFA500"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        // La puerta se dibuja en la parte superior central
        float doorLength = (viewWidth - 200) / 4; // Longitud de la puerta
        float doorStartX = (viewWidth / 2) - (doorLength / 2);
        float doorEndX = (viewWidth / 2) + (doorLength / 2);
        canvas.drawLine(doorStartX, 100, doorEndX, 100, paint);
        paint.setStyle(Paint.Style.FILL);

        // Dibujar círculos que representan las pinturas en las posiciones mostradas en la imagen
        paint.setColor(Color.parseColor("#FFA500"));
        float radius = 50f;

        // Círculo 1 (esquina superior izquierda)
        float cx1 = 200;
        float cy1 = 200;
        canvas.drawCircle(cx1, cy1, radius, paint);

        // Círculo 2 (esquina inferior izquierda)
        float cx2 = 200;
        float cy2 = viewHeight - 200;
        canvas.drawCircle(cx2, cy2, radius, paint);

        // Círculo 3 (esquina superior derecha)
        float cx3 = viewWidth - 200;
        float cy3 = 200;
        canvas.drawCircle(cx3, cy3, radius, paint);

        // Círculo 4 (esquina inferior derecha)
        float cx4 = viewWidth - 200;
        float cy4 = viewHeight - 200;
        canvas.drawCircle(cx4, cy4, radius, paint);

        // Dibujar el nombre de la galería centrado
        if (galleryName != null) {
            canvas.drawText(galleryName, viewWidth / 2, (viewHeight / 2) - (textPaint.descent() + textPaint.ascent()) / 2, textPaint);
        }
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
        invalidate(); // Redibujar la vista
    }
}
