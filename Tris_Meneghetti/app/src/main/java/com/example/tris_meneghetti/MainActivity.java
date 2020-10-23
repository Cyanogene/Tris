package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    //  https://stackoverflow.com/questions/23717558/android-draw-circle-in-custom-canvas-on-button-click
    // su drawCircle() passo degli input (tipo un bool che mi dice se disegnare o no)

    private String[][] griglia = new String[3][3];
    private myView[][] grigliaPulsanti = new myView[3][3];
    boolean turnoX = true;
    boolean vittoria = false;
    private TextView lbl_vittoria;
    private TextView lbl_punteggio;
    private Button btn_nuovaPartita;
    private myView btn0_view;
    private myView btn1_view;
    private int pareggio = 0;
    private int punteggioX = 0;
    private int punteggioO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaMatricePulsanti();

        btn_nuovaPartita = findViewById(R.id.btn_nuovaPartita);
        lbl_vittoria = findViewById(R.id.lbl_vittoria);
        lbl_punteggio = findViewById(R.id.lbl_punteggio);
        lbl_vittoria.setText("Turno di X");
        lbl_punteggio.setText(String.format("X: 0\n O: 0"));
        btn_nuovaPartita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nuovaPartita();
                lbl_vittoria.setText("Turno di X");
            }
        });
    }

    private void nuovaPartita() {
        vittoria = false;
        pareggio = 0;
        lbl_vittoria.setText("");
        griglia = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(true);
                grigliaPulsanti[i][k].nuovaPartita();
            }
        }
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
                grigliaPulsanti[i][k].setClickable(true);
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
                        pareggio++;
                        griglia[i][k] = "X";
                        ((myView) view).drawX();
                        grigliaPulsanti[i][k].setClickable(false);
                        turnoX = false;
                        lbl_vittoria.setText("Turno di O");
                    } else {
                        pareggio++;
                        griglia[i][k] = "O";
                        ((myView) view).drawO();
                        grigliaPulsanti[i][k].setClickable(false);
                        turnoX = true;
                        lbl_vittoria.setText("Turno di X");
                    }

                }
            }

        }

        controllo();
        if (vittoria)
            return;
        if (pareggio == 9) {
            lbl_vittoria.setText("Pareggio!");
        }

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
        if (!vittoria) {
            if (n == 3) {
                punteggioX++;
                victory("X");
            }

            if (n == -3) {
                punteggioO++;
                victory("O");
            }
        }
    }

    private void victory(String giocatore) {
        lbl_vittoria.setText(String.format("La %s ha vinto!", giocatore));
        vittoria = true;
        lbl_punteggio.setText(String.format("X: %d\n O: %d", punteggioX, punteggioO));
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(false);
            }
        }
    }
}