package com.example.bandeng.picuga;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class LanguageSettingsActivity extends AppCompatActivity {
    Button indonesian, english, back;
    SharedPreferences gamePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);

        indonesian = findViewById(R.id.language_indonesia);
        english =  findViewById(R.id.language_english);
        back = findViewById(R.id.language_settings_back);
        gamePreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        indonesian.setOnClickListener(setIndonesian);
        english.setOnClickListener(setEnglish);
        back.setOnClickListener(backListener);
    }

    private void restartInLocale(Locale locale)
    {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
        SharedPreferences.Editor edit = gamePreferences.edit();
        edit.putString("GAME_LANG", locale.getLanguage());
        edit.apply();
    }

    View.OnClickListener setIndonesian = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restartInLocale(new Locale("in"));
        }
    };

    View.OnClickListener setEnglish = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restartInLocale(new Locale("en"));
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
