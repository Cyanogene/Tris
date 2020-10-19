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

    public myView(Context context){
        super(context);
        paint = new Paint();
        paint.setColor(Color.parseColor("#21BBA6"));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);
    }

    private ArrayList<PointF> listOfPoints = new ArrayList<PointF>();
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    Paint paint;
    private int inte = 0;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //	starting point
        x1 = 50;
        y1 = 50;

        //	ending point
        x2 = getWidth() / 2 + getWidth() / 4;
        y2 = getHeight() / 2 + getHeight() / 4;

        Log.d("line xy xy", x1 + " : "+y1+" : "+x2 + " : "+y2);


        divideLineIntoEqualParts();
    }

    //	dividing line into 50 equal parts
    private void divideLineIntoEqualParts() {

        /*
         * Courtesy : www.dummies.com
         * (x,y) = (x1 + k(x2 - x1),y1 + k(y2 - y1))
         * */

        listOfPoints.clear();
        for (int k = 1; k <= 50; k++) {
            listOfPoints.add(new PointF(x1 + ((k * (x2 - x1)) / 50),y1 + (k * (y2 - y1)) / 50));
        }

        Log.d("listOfPoints : size : ",listOfPoints.size()+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(inte < listOfPoints.size()){
            canvas.drawLine(listOfPoints.get(0).x, listOfPoints.get(0).y, listOfPoints.get(inte).x,listOfPoints.get(inte).y, paint);
            inte++;

            if(inte < listOfPoints.size()){
                invalidate();
            }
        }

    }
}
