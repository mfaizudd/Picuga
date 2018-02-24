package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LevelCompleteActivity extends AppCompatActivity {


    SharedPreferences gamePref;
    final String SharedPrefFile = "com.example.bandeng.picuga";
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);

        gf = new GlobalFunction(getApplicationContext());
        gamePref = getSharedPreferences(SharedPrefFile, MODE_PRIVATE);
        TextView scoreText = findViewById(R.id.score_text);
        Button backButton = findViewById(R.id.button_back);
        Button infoButton = findViewById(R.id.button_info);

        scoreText.setText(String.format(Locale.getDefault(),"Score mu sekarang adalah: %d", gamePref.getInt("SCORE", 0)));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(LevelCompleteActivity.this, InfoActivity.class));
            }
        });
    }
}
