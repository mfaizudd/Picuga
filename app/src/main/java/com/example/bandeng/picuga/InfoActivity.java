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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.InputStream;

public class InfoActivity extends AppCompatActivity {

    SharedPreferences gamePreferences;
    GlobalFunction gf;
    TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        Button buttonStart = findViewById(R.id.button_play);
        Button buttonBack = findViewById(R.id.button_back);
        textDescription = findViewById(R.id.text_description);
        final VideoView videoInfo = findViewById(R.id.video_info);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                ((LinearLayout)view.getParent()).setGravity(Gravity.CENTER);
                Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videoa);
                videoInfo.setVideoURI(uri);
                videoInfo.start();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });
        try {
            InputStream is = getAssets().open("reog.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            textDescription.setText(new String(buffer));
            if(Build.VERSION.SDK_INT>26) {
                textDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            }
        }
        catch(Exception ex) {
            gf.showAlert(ex.getMessage());
        }
    }
}
