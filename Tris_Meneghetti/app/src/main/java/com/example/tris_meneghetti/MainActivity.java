package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int[][] griglia = new int[2][2];
    private int player1;
    private int player2;

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

}