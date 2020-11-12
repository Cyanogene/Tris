package com.example.tris_meneghetti;

import android.os.Handler;
import android.view.View;

import java.util.Random;

public class AI implements IplayerVS {

    public Controllo cont = new Controllo();

    public int onButtonClick(View view, Design[][] grigliaPulsanti, boolean turnoX, String[][] griglia, int pareggio) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (grigliaPulsanti[i][k] == view) {

                    griglia[i][k] = "X";
                    ((Design) view).drawX();
                    grigliaPulsanti[i][k].setClickable(false);
                }
            }
        }
        if (!controllaCelleVuote(griglia))
            return 0;
        else if (Menu.scelta == "EasyAI")
            return easyAI(griglia,grigliaPulsanti);
        else
            return impossibleAI(griglia, grigliaPulsanti);
    }

    private int easyAI(String[][] griglia, Design[][] grigliaPulsanti) {
        boolean valore = false;
        Random r = new Random();
        int n = r.nextInt(20);
        if (n > 2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (griglia[i][j] == null && !valore) {
                        delayedDraw(i, j, grigliaPulsanti);
                        griglia[i][j] = "O";
                        grigliaPulsanti[i][j].setClickable(false);
                        valore = true;
                    }
                }
            }
        }
        else
            return impossibleAI(griglia, grigliaPulsanti);

        return cont.controllo(griglia);
    }

    private int impossibleAI(String[][] griglia, Design[][] grigliaPulsanti) {
        int mossaMigliore = -1000;
        int riga = -1;
        int colonna = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (griglia[i][j] == null) {

                    griglia[i][j] = "O";
                    int moveVal = minimax(griglia,false);
                    griglia[i][j] = null;

                    if (moveVal > mossaMigliore) {
                        riga = i;
                        colonna = j;
                        mossaMigliore = moveVal;
                    }
                }
            }
        }

        delayedDraw(riga, colonna, grigliaPulsanti);
        griglia[riga][colonna] = "O";
        grigliaPulsanti[riga][colonna].setClickable(false);
        return cont.controllo(griglia);
    }

    // Ritarda l'animazione del simbolo del bot, diminuendo le possibilità di eventuali bug grafici
    private void delayedDraw(int row, int column, Design[][] grigliaPulsanti) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Design) grigliaPulsanti[row][column]).drawO();
            }
        }, 100);
    }

    // Metodo ricursivo che prova tutte le combinazioni possibili nella griglia per ogni cella
    private int minimax(String[][] griglia, boolean isMax) {
        int score = cont.controllo(griglia);
        if (score == 1)
            return score;

        if (score == -1)
            return score;

        if (!controllaCelleVuote(griglia))
            return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (griglia[i][j] == null) {

                        griglia[i][j] = "O";
                        best = Math.max(best, minimax(griglia, !isMax));
                        griglia[i][j] = null;
                    }
                }
            }
            return best;
        }

        else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (griglia[i][j] == null) {

                        griglia[i][j] = "X";
                        best = Math.min(best, minimax(griglia, !isMax));
                        griglia[i][j] = null;
                    }
                }
            }
            return best;
        }
    }

    private Boolean controllaCelleVuote(String griglia[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (griglia[i][j] == null)
                    return true;
        return false;
    }
}
