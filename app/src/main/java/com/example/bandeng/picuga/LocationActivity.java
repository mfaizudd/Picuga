package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LocationActivity extends AppCompatActivity {

    SharedPreferences gamePreferences;
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);

        Button levelOne = findViewById(R.id.level_1);
        Button buttonBack = findViewById(R.id.button_back);
        levelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(LocationActivity.this, Game.class));
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });
    }
}
