package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulty extends AppCompatActivity {


    SharedPreferences gamePreferences;
    final String gamePreferencesFile = "com.example.bandeng.picuga";
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences(gamePreferencesFile, MODE_PRIVATE);
        final SharedPreferences.Editor gamePreferencesEditor = gamePreferences.edit();

        Button buttonBack = (Button)findViewById(R.id.button_back);
        Button buttonEasy = (Button)findViewById(R.id.button_easy);
        Button buttonMedium = (Button)findViewById(R.id.button_medium);
        Button buttonHard = (Button)findViewById(R.id.button_hard);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(Difficulty.this, SelectLevel.class));
                gamePreferencesEditor.putInt("DIFFICULTY_TIME", 120);
                gamePreferencesEditor.apply();
            }
        });

        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(Difficulty.this, SelectLevel.class));
                gamePreferencesEditor.putInt("DIFFICULTY_TIME", 60);
                gamePreferencesEditor.apply();
            }
        });

        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(Difficulty.this, SelectLevel.class));
                gamePreferencesEditor.putInt("DIFFICULTY_TIME", 30);
                gamePreferencesEditor.apply();
            }
        });

    }
}
