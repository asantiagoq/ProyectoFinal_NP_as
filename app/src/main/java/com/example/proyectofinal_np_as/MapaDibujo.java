package com.example.proyectofinal_np_as;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MapaDibujo extends View {
    private Paint paint;
    private Paint textPaint;
    private int cellSize;
    private int marginLeft;
    private int marginTop;

    public interface OnGalleryClickListener {
        void onGalleryClick(String galleryName);
    }

    private OnGalleryClickListener listener;

    public MapaDibujo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnGalleryClickListener(OnGalleryClickListener listener) {
        this.listener = listener;
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

        // Calcular el tamaño de la celda en función del tamaño de la vista
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int mapWidth = 32;
        int mapHeight = 18;
        cellSize = Math.min(viewWidth / mapWidth, viewHeight / mapHeight);

        // Calcular márgenes para centrar el mapa
        marginLeft = (viewWidth - (mapWidth * cellSize)) / 2;
        marginTop = (viewHeight - (mapHeight * cellSize)) / 2;

        // Definición de los colores
        int colorUnusedZone = Color.rgb(169, 169, 169); // Gris oscuro
        int colorBathroom = Color.rgb(255, 182, 193); // Rosa
        int colorCorridor = Color.rgb(255, 255, 224); // Amarillo claro
        int colorGallery = Color.rgb(144, 238, 144); // Verde claro
        int colorStorage = Color.rgb(105, 105, 105); // Gris

        // Dibujar las áreas

        // Baños
        paint.setColor(colorBathroom);
        canvas.drawRect(marginLeft + 1 * cellSize, marginTop + 1 * cellSize, marginLeft + 5 * cellSize, marginTop + 4 * cellSize, paint);
        canvas.drawText("Baños", marginLeft + 3 * cellSize, marginTop + 2.5f * cellSize, textPaint);

        // Zona no usada a lado de baño
        paint.setColor(colorUnusedZone);
        canvas.drawRect(marginLeft + 5 * cellSize, marginTop + 1 * cellSize, marginLeft + 15 * cellSize, marginTop + 4 * cellSize, paint);
        canvas.drawText("Zona no usada", marginLeft + 10 * cellSize, marginTop + 2.5f * cellSize, textPaint);

        // ZNU
        paint.setColor(colorStorage);
        canvas.drawRect(marginLeft + 1 * cellSize, marginTop + 6 * cellSize, marginLeft + 9 * cellSize, marginTop + 9.5f * cellSize, paint);
        canvas.drawText("ZNU", marginLeft + 3 * cellSize, marginTop + 8 * cellSize, textPaint);

        // G. V
        paint.setColor(colorGallery);
        canvas.drawRect(marginLeft + 15 * cellSize, marginTop + 1 * cellSize, marginLeft + 20 * cellSize, marginTop + 5 * cellSize, paint);
        canvas.drawText("G. V", marginLeft + 17.5f * cellSize, marginTop + 3 * cellSize, textPaint);

        // G. IV
        canvas.drawRect(marginLeft + 20 * cellSize, marginTop + 1 * cellSize, marginLeft + 32 * cellSize, marginTop + 5 * cellSize, paint);
        canvas.drawText("G. IV", marginLeft + 26 * cellSize, marginTop + 3 * cellSize, textPaint);

        // G. VII
        canvas.drawRect(marginLeft + 5 * cellSize, marginTop + 6 * cellSize, marginLeft + 9 * cellSize, marginTop + 13 * cellSize, paint);
        canvas.drawText("G. VII", marginLeft + 7 * cellSize, marginTop + 8 * cellSize, textPaint);

        // G. VI
        canvas.drawRect(marginLeft + 9 * cellSize, marginTop + 10 * cellSize, marginLeft + 14 * cellSize, marginTop + 13 * cellSize, paint);
        canvas.drawText("G. VI", marginLeft + 11.5f * cellSize, marginTop + 11.5f * cellSize, textPaint);

        // G. III
        canvas.drawRect(marginLeft + 16 * cellSize, marginTop + 6 * cellSize, marginLeft + 19 * cellSize, marginTop + 14 * cellSize, paint);
        canvas.drawText("G. III", marginLeft + 17.5f * cellSize, marginTop + 9.5f * cellSize, textPaint);

        // G. II
        canvas.drawRect(marginLeft + 16 * cellSize, marginTop + 14 * cellSize, marginLeft + 29 * cellSize, marginTop + 17 * cellSize, paint);
        canvas.drawText("G. II", marginLeft + 22.5f * cellSize, marginTop + 15.5f * cellSize, textPaint);

        // G. I
        canvas.drawRect(marginLeft + 29 * cellSize, marginTop + 6 * cellSize, marginLeft + 32 * cellSize, marginTop + 17 * cellSize, paint);
        canvas.drawText("G. I", marginLeft + 30.5f * cellSize, marginTop + 11.5f * cellSize, textPaint);

        // C
        paint.setColor(colorBathroom);
        canvas.drawRect(marginLeft + 14 * cellSize, marginTop + 6 * cellSize, marginLeft + 16 * cellSize, marginTop + 13 * cellSize, paint);
        canvas.drawText("C", marginLeft + 15 * cellSize, marginTop + 9.5f * cellSize, textPaint);

        // Patio 1
        paint.setColor(colorCorridor);
        canvas.drawRect(marginLeft + 1 * cellSize, marginTop + 4 * cellSize, marginLeft + 32 * cellSize, marginTop + 6 * cellSize, paint);
        // Patio 2
        canvas.drawRect(marginLeft + 9 * cellSize, marginTop + 6 * cellSize, marginLeft + 14 * cellSize, marginTop + 10 * cellSize, paint);
        // Patio 3
        canvas.drawRect(marginLeft + 19 * cellSize, marginTop + 6 * cellSize, marginLeft + 29 * cellSize, marginTop + 14 * cellSize, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            String galleryName = getGalleryAtPosition(x, y);
            if (galleryName != null && listener != null) {
                listener.onGalleryClick(galleryName);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private String getGalleryAtPosition(float x, float y) {
        int col = (int) ((x - marginLeft) / cellSize);
        int row = (int) ((y - marginTop) / cellSize);

        if (col >= 15 && col < 20 && row >= 1 && row < 5) {
            return "G. V";
        } else if (col >= 20 && col < 32 && row >= 1 && row < 5) {
            return "G. IV";
        } else if (col >= 5 && col < 9 && row >= 6 && row < 13) {
            return "G. VII";
        } else if (col >= 9 && col < 14 && row >= 10 && row < 13) {
            return "G. VI";
        } else if (col >= 16 && col < 19 && row >= 6 && row < 14) {
            return "G. III";
        } else if (col >= 16 && col < 29 && row >= 14 && row < 17) {
            return "G. II";
        } else if (col >= 29 && col < 32 && row >= 6 && row < 17) {
            return "G. I";
        }
        return null;
    }
}
