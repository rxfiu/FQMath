package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.IslamicCalendar;
import android.security.ConfirmationNotAvailableException;

import java.util.List;

public class Graph {
    Paint axesPaint;
    float axesPaintWidth = 1;

    float scaleHeight = 12;

    Paint arrowsPaint;
    float arrowsPaintWidth = 1.5f;
    float arrowsHeight = 12;

    Canvas canvas;

    Bounds bounds;

    float scale = 100;

    List<Function> functions;

    public Bounds getBounds() {
        return bounds;
    }

    public float getScale() {
        return scale;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private Graph() {

    }

    public Graph(Canvas canvas) {
        this.canvas = canvas;
    }

    private void init() {
        setBounds();
        setPaint();

        drawCompleteAxes(canvas, axesPaint, arrowsPaint, scaleHeight, scale);
    }

    private void setBounds() {
        float centerX = canvas.getClipBounds().centerX();
        float centerY = canvas.getClipBounds().centerY();
        float endX = canvas.getClipBounds().right;
        float endY = canvas.getClipBounds().bottom;

        bounds = new Bounds(centerX, centerY, endX, endY);
    }

    private void setPaint() {
        axesPaint = new Paint();
        axesPaint.setColor(Color.BLACK);
        axesPaint.setStrokeWidth(axesPaintWidth);

        arrowsPaint = new Paint();
        arrowsPaint.setColor(Color.BLACK);
        arrowsPaint.setStrokeWidth(arrowsPaintWidth);
    }

    private void drawAxes(Canvas canvas, Paint paint) {
        canvas.drawLine(bounds.getCenterX(), 0, bounds.getCenterX(), bounds.getEndY(), paint);
        canvas.drawLine(0, bounds.getCenterY(), bounds.getEndX(), bounds.getCenterY(), paint);
    }

    private void drawArrows(Canvas canvas, Paint paint) {
        //arrow dx
        canvas.drawLine(bounds.getEndX(), bounds.getCenterY(), bounds.getEndX() - arrowsHeight, bounds.getCenterY() - arrowsHeight, paint);
        canvas.drawLine(bounds.getEndX(), bounds.getCenterY(), bounds.getEndX() - arrowsHeight, bounds.getCenterY() + arrowsHeight, paint);
        //arrow up
        canvas.drawLine(bounds.getCenterX(), 0, bounds.getCenterX() + arrowsHeight, 0 + arrowsHeight, paint);
        canvas.drawLine(bounds.getCenterX(), 0, bounds.getCenterX() - arrowsHeight, 0 + arrowsHeight, paint);
    }

    private void drawScale(Canvas canvas, Paint paint, float scaleHeight, float scale) {
        float scaleChanged = scale;

        boolean condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleChanged, bounds.getCenterY() + scaleHeight, bounds.getCenterX() + scaleChanged, bounds.getCenterY() - scaleHeight, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterX() + scaleChanged) < bounds.getEndX();
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() - scaleChanged, bounds.getCenterY() + scaleHeight, bounds.getCenterX() - scaleChanged, bounds.getCenterY() - scaleHeight, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterX() - scaleChanged) > 0;
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleHeight, bounds.getCenterY() + scaleChanged, bounds.getCenterX() - scaleHeight, bounds.getCenterY() + scaleChanged, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterY() + scaleChanged) < bounds.getEndY();
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleHeight, bounds.getCenterY() - scaleChanged, bounds.getCenterX() - scaleHeight, bounds.getCenterY() - scaleChanged, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterY() - scaleChanged) > 0;
        }
    }

    private void drawCompleteAxes(
            Canvas canvas, Paint axesPaint, Paint arrowsPaint, float scaleHeight, float scale){
        drawAxes(canvas, axesPaint);
        drawArrows(canvas, arrowsPaint);
        drawScale(canvas, axesPaint, scaleHeight, scale);
    }

    private void drawFunction(Canvas canvas, Paint paint) {

    }

}