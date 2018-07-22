package com.example.bandeng.picuga;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LossActivity extends AppCompatActivity {

    SharedPreferences gamePreferences;
    TextView scoreTextView;
    Button buttonBack;
    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss);
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        scoreTextView = findViewById(R.id.score_text);
        buttonBack = findViewById(R.id.button_back);
        gf = new GlobalFunction(getApplicationContext());

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });
    }
}
