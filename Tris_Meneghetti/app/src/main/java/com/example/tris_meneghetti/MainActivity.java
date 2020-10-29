package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean turnoX;
    private boolean vittoria = false;

    private int pareggio = 0;
    private int punteggioX = 0;
    private int punteggioO = 0;

    private String[][] griglia = new String[3][3];
    private Design[][] grigliaPulsanti = new Design[3][3];

    private TextView lbl_vittoria;
    private TextView lbl_punteggio;
    private ImageButton btn_nuovaPartita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaMatricePulsanti();

        btn_nuovaPartita = findViewById(R.id.btn_nuovaPartita);
        lbl_vittoria = findViewById(R.id.lbl_vittoria);
        lbl_punteggio = findViewById(R.id.lbl_punteggio);
        lbl_punteggio.setText(String.format("X: 0\n O: 0"));
        init();

        btn_nuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuovaPartita();
                btn_nuovaPartita.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void init() {
        Random turno = new Random();
        turnoX = turno.nextBoolean();
        if (turnoX)
            lbl_vittoria.setText("Turno di X");
        else
            lbl_vittoria.setText("Turno di O");
    }

    private void nuovaPartita() {
        vittoria = false;
        pareggio = 0;
        griglia = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(true);
                grigliaPulsanti[i][k].nuovaPartita();
            }
        }
        init();
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

    private void controllo() {
        for (int i = 0; i < 3; i++) {
            rowCheck(i);
            columnCheck(i);
        }
        diagonalCheck1();
        diagonalCheck2();

        if (vittoria)
            return;
        if (pareggio >= 9) {
            lbl_vittoria.setText("Pareggio!");
            btn_nuovaPartita.setVisibility(View.VISIBLE);
        }
    }

    private void onButtonClick(View view) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (grigliaPulsanti[i][k] == view) {
                    if (turnoX) {
                        griglia[i][k] = "X";
                        ((Design) view).drawX();
                        turnoX = false;
                        lbl_vittoria.setText("Turno di O");
                    } else {
                        griglia[i][k] = "O";
                        ((Design) view).drawO();
                        turnoX = true;
                        lbl_vittoria.setText("Turno di X");
                    }
                    pareggio++;
                    grigliaPulsanti[i][k].setClickable(false);
                }
            }
        }
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
        lbl_punteggio.setText(String.format("X: %d\n O: %d", punteggioX, punteggioO));
        vittoria = true;
        btn_nuovaPartita.setVisibility(View.VISIBLE);

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(false);
            }
        }
    }
}