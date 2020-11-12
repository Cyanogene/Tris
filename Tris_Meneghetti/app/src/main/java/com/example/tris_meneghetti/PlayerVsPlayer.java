package com.example.tris_meneghetti;

import android.view.View;

public class PlayerVsPlayer implements IplayerVS {

    private Controllo cont = new Controllo();

    public int onButtonClick(View view, Design[][] grigliaPulsanti, boolean turnoX, String[][] griglia, int pareggio) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {

                if (grigliaPulsanti[i][k] == view) {

                    if (turnoX) {
                        griglia[i][k] = "X";
                        ((Design) view).drawX();

                    } else {
                        griglia[i][k] = "O";
                        ((Design) view).drawO();
                    }
                    pareggio++;
                    grigliaPulsanti[i][k].setClickable(false);
                }
            }
        }
        return cont.controllo(griglia);
    }
}
