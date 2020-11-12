package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean turnoX;

    private int pareggio = 0;
    private int punteggioX = 0;
    private int punteggioO = 0;

    private String[][] griglia = new String[3][3];
    private Design[][] grigliaPulsanti = new Design[3][3];
    private IplayerVS players;

    private TextView lbl_vittoria;
    private TextView lbl_punteggio;
    private ImageButton btn_nuovaPartita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assegnaMetodiPulsanti();
        init();
        assegnaInizioGiocatore();
        btn_nuovaPartita.setOnClickListener(view -> {
            lbl_vittoria.setText("E' il tuo turno");
            nuovaPartita();
        });
    }

    private void init(){
        btn_nuovaPartita = findViewById(R.id.btn_nuovaPartita);
        lbl_vittoria = findViewById(R.id.lbl_vittoria);
        lbl_punteggio = findViewById(R.id.lbl_punteggio);
        lbl_punteggio.setText("X: 0\n O: 0");
        lbl_vittoria.setText("E' il tuo turno");
    }

    // Giocatore contro giocatore: il turno viene scelto a caso. Contro il bot: parte sempre il giocatore umano.
    private void assegnaInizioGiocatore() {
        if (Menu.scelta == "PlayerVSPlayer") {
            Random turno = new Random();
            turnoX = turno.nextBoolean();
            if (turnoX)
                lbl_vittoria.setText("Turno di X");
            else
                lbl_vittoria.setText("Turno di O");
        }
        else
            turnoX = true;
    }

    private void nuovaPartita() {
        pareggio = 0;
        griglia = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(true);
                grigliaPulsanti[i][k].nuovaPartita();
            }
        }
        assegnaInizioGiocatore();
    }

    private void getDifficulty() {
        if (Menu.scelta == "PlayerVSPlayer")
            players = new PlayerVsPlayer();
        else
            players = new AI();
    }

    // Gestisce i pulsanti (sono in realtà delle custom views adattate come pulsanti)
    private void assegnaMetodiPulsanti() {
        int a = 0;
        getDifficulty();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++, a++) {

                String buttonID = "button" + a;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                grigliaPulsanti[i][k] = findViewById(resID);
                grigliaPulsanti[i][k].setClickable(true);
                //
                // le righe soprastanti creano e rendono cliccabili le views
                //
                grigliaPulsanti[i][k].setOnClickListener(view ->
                {
                    pareggio++;
                    int v = players.onButtonClick(view, grigliaPulsanti, turnoX, griglia, pareggio);
                    switchTurn();
                    checkWinner(v);
                });
            }
        }
    }

    // Controlla se qualcuno ha vinto
    private void checkWinner(int v) {
        if (v == 1) {
            punteggioX++;
            victory("X");
        } else if (v == -1) {
            punteggioO++;
            victory("O");
        } else if (pareggio >= 9) {
            lbl_vittoria.setText("Pareggio!");
        }
    }

    // Cambia il turno.
    private void switchTurn() {
        if (Menu.scelta == "PlayerVSPlayer") {
            turnoX = !turnoX;
            lbl_vittoria.setText(String.format("Turno di %s", turnoX ? "X" : "O"));
        } else
            pareggio++; // Serve per fare in modo che la mossa del bot venga riconosciuta dal programma
    }

    // Filtra e assegna la vittoria
    private void victory(String giocatore) {
        if (Menu.scelta != "PlayerVSPlayer") {
            if (giocatore == "O") {
                giocatore = "X";
                punteggioX++;
                punteggioO--;
            } else {
                giocatore = "O";
                punteggioO++;
                punteggioX--;
            }
        }

        lbl_vittoria.setText(String.format("La %s ha vinto!", giocatore));
        lbl_punteggio.setText(String.format("X: %d\n O: %d", punteggioX, punteggioO));

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                grigliaPulsanti[i][k].setClickable(false);
            }
        }
    }
}