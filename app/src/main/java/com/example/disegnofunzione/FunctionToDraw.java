package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.Objects;

public class FunctionToDraw {
    private int color;
    private Function function;

    private FunctionToDraw() {

    }
    private FunctionToDraw(int color, Function function) {
        this.color = color;
        this.function = function;
    }

    public void drawFunction(Graph graph, Paint paint, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);

        Path path = drawPath(graph);

        graph.getCanvas().drawPath(path, paint);
    }
    public void drawFunction(Graph graph, Paint paint) {
        drawFunction(graph,paint,this.color);
    }
    public void eraseFunction(Graph graph, Paint paint) {
        drawFunction(graph,paint,Color.WHITE);
    }


    private Path drawPath(Graph graph) {
        Path path = new Path();
        boolean move = true;
        for(float i = 0; i < graph.bounds.getEndX(); i++) {
            float x = i;
            try {
                float y = f(x, graph);
                if(move) {
                    path.moveTo(x, y);
                    move = false;
                }
                else {
                    path.lineTo(x, y);
                }
            }
            catch (Exception e) {
                move = true;
            }
        }
        return path;
    }

    private float f(float x, Graph graph) {
        x -= graph.bounds.getCenterX();
        double res = function.Evaluate(x / graph.getScale());
        res = -res * graph.getScale() + graph.bounds.getCenterY();

        return (float)(res);
    }
}
