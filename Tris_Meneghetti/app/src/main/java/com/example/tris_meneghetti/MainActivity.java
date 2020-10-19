package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[][] griglia = new int[3][3];
    private int player1;
    private int player2;
    private Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myView(this));
        //  https://stackoverflow.com/questions/23717558/android-draw-circle-in-custom-canvas-on-button-click
        // su drawCircle() passo degli input (tipo un bool che mi dice se disegnare o no)

        /*
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);
        }
         */
    }


    private void controllo(int i)    // da void a int se si fa if (controllo(i) == 1) //player 1 has won!!
    {
        rowCheck(i);
        columnCheck(i);
        diagonalCheck1();
        diagonalCheck2();
    }


    private void rowCheck(int i) {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[i][k] == 1) n++;
            if (griglia[i][k] == -1) n--;
        }
        controlloVittoria(n);
    }

    private void columnCheck(int i) {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[k][i] == 1) n++;
            if (griglia[k][i] == -1) n--;
        }
        controlloVittoria(n);
    }

    private void diagonalCheck1() {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[k][k] == 1) n++;
            if (griglia[k][k] == -1) n--;
        }
        controlloVittoria(n);
    }

    private void diagonalCheck2() {
        int n = 0;
        int i = 2;
        for (int k = 0; k < 3; k++) {
            if (griglia[k][i] == 1) n++;
            if (griglia[k][i] == -1) n--;
            i--;
        }
        controlloVittoria(n);
    }

    private void controlloVittoria(int n) {
        if (n == 3)
            victory(1);
        else if (n == -3)
            victory(2);
    }

    private void victory(int giocatore) {


    }

    private int startX = 0;
    private int startY = 0;

    private int endX = 0;
    private int endY = 0;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG) {
        {
            setDither(true);
            setColor(Color.RED);
        }
    };


    @Override
    public void onClick(View v) {
        ((Button) v).setText("ciao");
    }
}