package com.example.tris_meneghetti;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class myView extends View {

    private int startX = 0;
    private int startY = 0;

    private int endX = 0;
    private int endY = 0;

    private int radius = 60;
    private int mPivotX = 0;
    private int mPivotY = 0;
    public static Canvas mCanvas;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.RED);
            setStrokeWidth(10);
            setStrokeCap(Cap.ROUND);
        }
    };

    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawO();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(canvas);

        canvas.drawCircle(mPivotX, mPivotY, radius, paint);

        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.drawLine(100,startY,100 - endX,endY,paint);

        if (endX != 100 && endY != 100) { // set end points
            endY+=10;
            endX+=10;

        }
        invalidate();

    }

    private void drawX() {

    }

    public void drawO() {
        int minX = radius * 2;
        int maxX = getWidth() - (radius * 2);

        int minY = radius * 2;
        int maxY = getHeight() - (radius * 2);

        mPivotX = 50;
        mPivotY = 50;

        //important. Refreshes the view by calling onDraw function
        invalidate();
    }


}
