package com.example.bandeng.picuga;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainMenu extends AppCompatActivity {

    SharedPreferences gamePreferences;
    GlobalFunction gf;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        gf = new GlobalFunction(getApplicationContext());
        lang = gamePreferences.getString("GAME_LANG", "in");
        AppUtil.setLocale(this, new Locale(lang));
        setContentView(R.layout.activity_main_menu);

        Button main = (Button)findViewById(R.id.button_main);
        Button buttonSettings = (Button)findViewById(R.id.button_settings);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(MainMenu.this, SelectLevel.class));
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(MainMenu.this, Settings.class));
            }
        });
    }

    public void exit(View view) {
        gf.playBubble();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String newLang = gamePreferences.getString("GAME_LANG", "in");
        if(!lang.equals(newLang)) {
            recreate();
            lang = newLang;
        }
    }
}
