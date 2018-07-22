package com.example.bandeng.picuga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SelectLevel extends AppCompatActivity {
    SharedPreferences gamePreferences;
    GlobalFunction gf;
    Button buttonBack;
    RecyclerView levelRecycler;
    LevelRecyclerViewAdapter adapter;
    JSONObject levels;
    int levelReached;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        levels = readJSON(R.raw.questions);
        levelReached = gamePreferences.getInt("LEVEL_REACHED", 0);
        adapter = new LevelRecyclerViewAdapter(getlevels(), SelectLevel.this);

        buttonBack = findViewById(R.id.select_level_button_back);
        levelRecycler = findViewById(R.id.level_recycler);

        levelRecycler.setAdapter(adapter);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                finish();
            }
        });
    }

    List<LevelModel> getlevels() {
        List<LevelModel> items = new ArrayList<>();
        try {
            for(int i = 0; i < levels.getJSONArray("quiz").length(); i++) {
                if(levels.getJSONArray("quiz").getJSONObject(i).getString("status").equals("build")) {
                    items.add(new LevelModel(i, "build"));
                } else if (i>levelReached) {
                    items.add(new LevelModel(i, "locked"));
                } else {
                    items.add(new LevelModel(i, "playable"));
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public JSONObject readJSON(int id) {
        JSONObject result = null;
        try {
            InputStream stream = getResources().openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder resultStr = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null) {
                resultStr.append(line);
                resultStr.append("\n");
            }
            result = new JSONObject(resultStr.toString());
        } catch (Exception ex) {
            Log.d("JSON READ ERROR", ex.getMessage());
        }
        return result;
    }
}
