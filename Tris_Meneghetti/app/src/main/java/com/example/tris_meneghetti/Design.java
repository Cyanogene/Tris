package com.example.tris_meneghetti;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Design extends View {

    private boolean drawXAnimation = false;
    private boolean drawYAnimation = false;
    private boolean clear = false;

    private int endX = 0;
    private int endY = 0;
    private int angle = 0;

    private Paint paintX = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.rgb(84, 185, 255));
            setStrokeWidth(60);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Cap.ROUND);
        }
    };

    private Paint paintO = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.rgb(255, 128, 128));
            setStrokeWidth(40);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Cap.ROUND);
        }
    };

    private Paint paintXWhite = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.parseColor("#2A2A2A"));
            setStrokeWidth(40);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Cap.ROUND);
        }
    };

    public Design(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public Design(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // CANCELLA TUTTE LE "X" E "O"
        if (clear) {
            canvas.drawLine(getWidth() / 2, getWidth() / 2, endX + 30, endY + 30, paintXWhite);

            if (endX < getWidth() - 110 && endY < getWidth() - 110) {
                endY += 5;
                endX += 5;
                postInvalidateDelayed(5);
            } else {
                clear = false;
                return;
            }
        }

        // DISEGNA UNA "X"
        if (drawXAnimation) {
            canvas.drawLine(getWidth() / 2, getWidth() / 2, endX + 30, endY + 30, paintX);
            canvas.drawLine(getWidth() / 2, getWidth() / 2, getWidth() - 30 - endX, endY + 30, paintX);
            canvas.drawLine(getWidth() / 2, getWidth() / 2, endX + 30, getWidth() - 30 - endX, paintX);
            canvas.drawLine(getWidth() / 2, getWidth() / 2, getWidth() - 30 - endX, getWidth() - 30 - endX, paintX);

            if (endX < getWidth() - 110 && endY < getWidth() - 110) {
                endY += 7;
                endX += 7;
                postInvalidateDelayed(2);
            } else {
                drawXAnimation = false;
            }
        }

        // DISEGNA UNA "O"
        if (drawYAnimation) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, angle, paintO);

            if (angle < getWidth() / 2 - 60) {
                angle += 8;
                postInvalidateDelayed(3);
            } else {
                drawYAnimation = false;
            }
        }
    }

    public void nuovaPartita() {
        clear = true;
        invalidate();
    }

    public void drawX() {
        endX = 0;
        endY = 0;
        drawXAnimation = true;
        invalidate();
    }

    public void drawO() {
        angle = 0;
        drawYAnimation = true;
        invalidate();
    }
}