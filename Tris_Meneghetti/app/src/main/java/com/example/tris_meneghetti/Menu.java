package com.example.tris_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    public static String scelta;

    private Button humanPlayers;
    private Button easyAI;
    private Button impossibleAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        onButtonsClick();
    }

    private void init() {
        humanPlayers = findViewById(R.id.btn_players);
        easyAI = findViewById(R.id.btn_easyAI);
        impossibleAI = findViewById(R.id.btn_impossibleAI);
    }

    private void onButtonsClick() {
        humanPlayers.setOnClickListener(view -> {
            scelta = "PlayerVSPlayer";
            cambiaSchermata();
        });
        easyAI.setOnClickListener(view -> {
            scelta = "EasyAI";
            cambiaSchermata();
        });
        impossibleAI.setOnClickListener(view -> {
            scelta = "ImpossibleAI";
            cambiaSchermata();
        });

    }

    public void cambiaSchermata() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}