package com.example.tris_meneghetti;

import android.view.View;

public interface IplayerVS {
    int onButtonClick(View view, Design[][] grigliaPulsanti, boolean turnoX, String[][] griglia, int pareggio);
}
