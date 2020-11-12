package com.example.tris_meneghetti;

public class Controllo {

    private int stato;
    private boolean vittoria = false;

    private String[][] griglia;

    public int controllo(String[][] grigl) {
        griglia = grigl;
        vittoria = false;

        for (int i = 0; i < 3; i++) {
            rowCheck(i);
            columnCheck(i);
        }
        diagonalCheck1();
        diagonalCheck2();
        return stato;
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

    // Il metodo viene usato sia per giocatore vs bot che per giocatore vs giocatore:
    // nel primo caso, il valore assegnato serve al bot per capire se la mossa è vantaggiosa oppure no.
    // nel secondo caso, serve per distinguere i due giocatori
    private void controlloVittoria(int n) {
        if (!vittoria) {
            if (n == 3) {
                if (Menu.scelta == "PlayerVSPlayer") {
                    stato = 1;
                } else {
                    stato = -1;
                }
                vittoria = true;

            } else if (n == -3) {
                if (Menu.scelta == "PlayerVSPlayer") {
                    stato = -1;
                } else {
                    stato = 1;
                }
                vittoria = true;

            } else {
                stato = 0;
            }
        }
    }
}
