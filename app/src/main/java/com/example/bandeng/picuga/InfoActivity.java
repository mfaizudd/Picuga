package com.example.bandeng.picuga;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.InputStream;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    SharedPreferences gamePreferences;
    GlobalFunction gf;
    TextView textDescription, levelNameView;
    Button buttonBack;
    ImageView puzzlePreview;
    int level;
    String levelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        level = getIntent().getIntExtra("LEVEL", 0);
        levelName = getIntent().getStringExtra("LEVEL_NAME");
        buttonBack = findViewById(R.id.button_back);
        textDescription = findViewById(R.id.text_description);
        puzzlePreview = findViewById(R.id.info_puzzle_complete);
        levelNameView = findViewById(R.id.level_name_info);

        levelNameView.setText(levelName);
        GlideApp.with(getApplicationContext()).load(Uri.parse(String.format(Locale.getDefault(), "file:///android_asset/puzzles/%d/pzl_complete.jpg", level+1))).into(puzzlePreview);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });
        try {
            InputStream is = getAssets().open(String.format(Locale.getDefault(), "puzzles/%d/article.txt", level+1));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            textDescription.setText(new String(buffer));
            if(Build.VERSION.SDK_INT>26) {
                textDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
