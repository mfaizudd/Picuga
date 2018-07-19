package com.example.bandeng.picuga;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    SharedPreferences gamePreferences;
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        gf = new GlobalFunction(getApplicationContext());

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
}
