package com.example.proyectofinal_np_as;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MapaDibujo extends View {
    private Paint paint;
    private Paint textPaint;
    private int cellSize = 50; // Tamaño de cada celda de la cuadrícula

    public MapaDibujo(Context context, @Nullable AttributeSet attrs) {
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
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Definición de los colores
        int colorUnusedZone = Color.rgb(169, 169, 169); // Gris oscuro
        int colorBathroom = Color.rgb(255, 182, 193); // Rosa
        int colorCorridor = Color.rgb(255, 255, 224); // Amarillo claro
        int colorGallery = Color.rgb(144, 238, 144); // Verde claro
        int colorStorage = Color.rgb(105, 105, 105); // Gris

        // Dibujar las áreas

        // Baños
        paint.setColor(colorBathroom);
        canvas.drawRect(1 * cellSize, 1 * cellSize, 5 * cellSize, 4 * cellSize, paint);
        canvas.drawText("Baños", 3 * cellSize, 2.5f * cellSize, textPaint);

        // zona no usada a lado de baño
        paint.setColor(colorUnusedZone);
        canvas.drawRect(5 * cellSize, 1 * cellSize, 15 * cellSize, 4 * cellSize, paint);
        canvas.drawText("           Zona no usada", 8 * cellSize, 2.5f * cellSize, textPaint);

        // ZNU
        canvas.drawRect(1 * cellSize, 6 * cellSize, 9 * cellSize, 9.5f * cellSize, paint);
        canvas.drawText("ZNU", 3 * cellSize, 8 * cellSize, textPaint);

        // G. V
        paint.setColor(colorGallery);
        canvas.drawRect(15 * cellSize, 1 * cellSize, 20 * cellSize, 5 * cellSize, paint);
        canvas.drawText("G. V", 17.5f * cellSize, 3 * cellSize, textPaint);

        // G. IV
        canvas.drawRect(20 * cellSize, 1 * cellSize, 32 * cellSize, 5 * cellSize, paint);
        canvas.drawText("G. IV", 26 * cellSize, 3 * cellSize, textPaint);

        // G. VII
        canvas.drawRect(5 * cellSize, 6 * cellSize, 9 * cellSize, 13 * cellSize, paint);
        canvas.drawText("G. VII", 7 * cellSize, 8 * cellSize, textPaint);

        // G. VI
        canvas.drawRect(9 * cellSize, 10 * cellSize, 14 * cellSize, 13 * cellSize, paint);
        canvas.drawText("G. VI", 11.5f * cellSize, 11.5f * cellSize, textPaint);

        // G. III
        canvas.drawRect(16 * cellSize, 6 * cellSize, 19 * cellSize, 14 * cellSize, paint);
        canvas.drawText(" G. III", 17 * cellSize, 9.5f * cellSize, textPaint);

        // G. II
        canvas.drawRect(16 * cellSize, 14 * cellSize, 29 * cellSize, 17 * cellSize, paint);
        canvas.drawText("                                                               G. II", 18 * cellSize, 15 * cellSize, textPaint);

        // G. I
        canvas.drawRect(29 * cellSize, 6 * cellSize, 32 * cellSize, 17 * cellSize, paint);
        canvas.drawText("             G. I", 29 * cellSize, 11.5f * cellSize, textPaint);

        // C
        paint.setColor(colorBathroom);
        canvas.drawRect(14 * cellSize, 6 * cellSize, 16 * cellSize, 13 * cellSize, paint);
        canvas.drawText("C", 15 * cellSize, 9.5f * cellSize, textPaint);

        // Patio 1
        paint.setColor(colorCorridor);
        canvas.drawRect(1 * cellSize, 4 * cellSize, 32 * cellSize, 6 * cellSize, paint);
        // Patio 2
        canvas.drawRect(9 * cellSize, 6 * cellSize, 14 * cellSize, 10 * cellSize, paint);
        // Patio 3
        canvas.drawRect(19 * cellSize, 6 * cellSize, 29 * cellSize, 14 * cellSize, paint);
    }
}
