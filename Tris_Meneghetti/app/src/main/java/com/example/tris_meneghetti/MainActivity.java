package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //  https://stackoverflow.com/questions/23717558/android-draw-circle-in-custom-canvas-on-button-click
    // su drawCircle() passo degli input (tipo un bool che mi dice se disegnare o no)

    private String[][] griglia = new String[3][3];
    private View[][] grigliaPulsanti = new View[3][3];
    boolean turnoX = true;
    boolean vittoria = false;
    private TextView lbl_vittoria;
    private Button btn_nuovaPartita;
    private myView btn0_view;
    private myView btn1_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaMatricePulsanti();

        btn_nuovaPartita = findViewById(R.id.btn_nuovaPartita);
        lbl_vittoria = findViewById(R.id.lbl_vittoria);
        btn_nuovaPartita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nuovaPartita();

            }
        });
    }

    private void nuovaPartita() {
        vittoria = false;
        lbl_vittoria.setText("");
        griglia = new String[3][3];
        turnoX = true;
    }

    private void creaMatricePulsanti() {
        int a = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                String buttonID = "button" + a;
                a++;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                grigliaPulsanti[i][k] = findViewById(resID);
                grigliaPulsanti[i][k].setOnClickListener(view -> onButtonClick(view));
            }
        }
    }

    private void controllo()    // da void a int se si fa if (controllo(i) == 1) //player 1 has won!!
    {
        for (int i = 0; i < 3; i++) {
            rowCheck(i);
            columnCheck(i);
        }

        diagonalCheck1();
        diagonalCheck2();
    }

    private void onButtonClick(View view) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (grigliaPulsanti[i][k] == view) {
                    if (turnoX) {
                        griglia[i][k] = "X";
                        ((myView) view).drawX();
                        turnoX = false;
                    }
                    else {
                        griglia[i][k] = "O";
                        ((myView) view).drawO();
                        turnoX = true;
                    }

                }
            }

        }

        if (vittoria)
            return;

        controllo();
    }


    private void rowCheck(int i) {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[i][k] == "X") n++;
            if (griglia[i][k] == "O") n--;
        }
        controlloVittoria(n);
    }

    private void columnCheck(int i) {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[k][i] == "X") n++;
            if (griglia[k][i] == "O") n--;
        }
        controlloVittoria(n);
    }

    private void diagonalCheck1() {
        int n = 0;
        for (int k = 0; k < 3; k++) {
            if (griglia[k][k] == "X") n++;
            if (griglia[k][k] == "O") n--;
        }
        controlloVittoria(n);
    }

    private void diagonalCheck2() {
        int n = 0;
        int i = 2;
        for (int k = 0; k < 3; k++) {
            if (griglia[i][k] == "X") n++;
            if (griglia[i][k] == "O") n--;
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
        lbl_vittoria.setText("VITTORIA" + giocatore);
        vittoria = true;
    }

    @Override
    public void onClick(View v) {
//        if (vittoria)
//            return;
//
//        for (int i = 0; i < 3; i++) {
//            for (int k = 0; k < 3; k++) {
//                if (grigliaPulsanti[i][k] == ((Button) v)) {
//                    if (turnoX) {
//                        griglia[i][k] = "X";
//                        turnoX = false;
//                    } else {
//                        griglia[i][k] = "O";
//                        turnoX = true;
//                    }
//                }
//            }
//        }
//        controllo();
    }
}