package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    SharedPreferences gamePreferences;
    SharedPreferences.Editor gamePreferencesEdit;
    MediaPlayer player;
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);

        Button backButton = (Button)findViewById(R.id.button_back);
        Button buttonAudioSettings = (Button)findViewById(R.id.button_audio_settings);
        Button buttonGraphicSettings = (Button)findViewById(R.id.button_graphic_settings);
        Button buttonLanguageSettings = (Button)findViewById(R.id.button_language_settings);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });

        buttonAudioSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(Settings.this, AudioSettings.class));
            }
        });

        buttonGraphicSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
            }
        });

        buttonLanguageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
            }
        });
    }
}
