package com.example.tris_meneghetti;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class myView extends View implements View.OnClickListener{

    private int startX = 30;
    private int startY = 30;

    private int endX = 0;
    private int endY = 0;
    private int angle =0;

    private int radius = 60;
    private int mPivotX = 0;
    private int mPivotY = 0;
    public boolean drawXAnimation = false;

    private Paint paintX = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.RED);
            setStrokeWidth(30);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Cap.ROUND);

        }
    };
    private Paint paintO = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.RED);
            setStrokeWidth(40);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Cap.ROUND);
        }
    };
    private boolean drawYAnimation = false;

    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        //drawO();
    }
    public myView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setClickable(true);
    }

/*
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            return performClick();
        }
        return true;
    }


 */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (drawXAnimation) {
            canvas.drawLine(startX, startY, endX, endY, paintX);
            canvas.drawLine(30, getWidth() -30, endX, getWidth() - endX, paintX);
            if (endX < getWidth() - 30 && endY < getWidth() -30) {
                endY += 20;
                endX += 20;
                postInvalidateDelayed(5);
            }
            else{
                drawXAnimation = false;
            }
        }
        if (drawYAnimation) {
            //canvas.drawLine(startX, startY, endX, endY, paint);
           // canvas.drawLine(10, 150, endX, 160 - endX, paint);
            //canvas.drawArc(0,0,getWidth(),getHeight(),0,angle,true,paint);
            canvas.drawCircle(getWidth()/2,getHeight()/2,angle, paintO);
            if (angle<getWidth()/2 - 40) {
                angle += 10;
                postInvalidateDelayed(5);
            }
            else{
                drawYAnimation = false;
            }
        }

    }
    //canvas.drawCircle(mPivotX, mPivotY, radius, paint);


    public void drawX() {
        drawXAnimation = true;
        invalidate();
    }

    public void drawO() {

//        int minX = radius * 2;
//        int maxX = getWidth() - (radius * 2);
//
//        int minY = radius * 2;
//        int maxY = getHeight() - (radius * 2);
//
//        mPivotX = 50;
//        mPivotY = 50;
//
//        //important. Refreshes the view by calling onDraw function
//        //invalidate();
        drawYAnimation = true;
        invalidate();
    }


    @Override
    public void onClick(View v) {
        drawX();
    }

}