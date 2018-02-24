package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectLevel extends AppCompatActivity {
    SharedPreferences gamePreferences;
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);

        Button buttonBack = findViewById(R.id.button_back);
        Button buttonOne = findViewById(R.id.button_1);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(SelectLevel.this, LocationActivity.class));
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
