package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.Locale;

public class LevelCompleteActivity extends AppCompatActivity {


    SharedPreferences gamePref;
    final String SharedPrefFile = "com.example.bandeng.picuga";
    GlobalFunction gf;
    int level;
    TextView scoreText, levelNameView;
    Button backButton;
    Button infoButton;
    String levelName;
    ImageView puzzleCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);

        gf = new GlobalFunction(getApplicationContext());
        gamePref = getSharedPreferences(SharedPrefFile, MODE_PRIVATE);
        level = getIntent().getIntExtra("LEVEL",0);
        try {
            levelName = AppUtil.readJSON(R.raw.questions, getApplicationContext()).getJSONArray("quiz").getJSONObject(level).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        scoreText = findViewById(R.id.score_text);
        backButton = findViewById(R.id.button_back);
        infoButton = findViewById(R.id.button_info);
        puzzleCompleted = findViewById(R.id.level_complete_puzzle_preview);
        levelNameView = findViewById(R.id.level_name_complete);

        long score = getIntent().getLongExtra("SCORE", 0);
        int seconds = (int) (score / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        scoreText.setText(String.format(Locale.getDefault(),"Score mu sekarang adalah: %02d:%02d", minutes, seconds));
        levelNameView.setText(levelName);
        GlideApp.with(getApplicationContext()).load(Uri.parse(String.format(Locale.getDefault(), "file:///android_asset/puzzles/%d/pzl_complete.jpg", level+1))).into(puzzleCompleted);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                startActivity(new Intent(LevelCompleteActivity.this, SelectLevel.class));
                finish();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                Intent intent = new Intent(LevelCompleteActivity.this, InfoActivity.class);
                intent.putExtra("LEVEL", level);
                intent.putExtra("LEVEL_NAME", levelName);
                startActivity(intent);
            }
        });
    }
}
