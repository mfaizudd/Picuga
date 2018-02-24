package com.example.bandeng.picuga;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class AudioSettings extends AppCompatActivity {
    SharedPreferences gamePreferences;
    SharedPreferences.Editor gamePreferencesEdit;

    SeekBar seekbarMusic;
    SeekBar seekbarEffect;
    SeekBar seekbarButton;

    GlobalFunction gf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_settings);

        gf = new GlobalFunction(getApplicationContext());
        gamePreferences = getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);

        Button buttonBack = (Button)findViewById(R.id.button_back);
        seekbarMusic = (SeekBar)findViewById(R.id.seekbar_music);
        seekbarEffect = (SeekBar)findViewById(R.id.seekbar_effect);
        seekbarButton = (SeekBar)findViewById(R.id.seekbar_button);

        setSeekBar(seekbarMusic, gamePreferences.getInt("MUSIC_VOLUME", 70));
        setSeekBar(seekbarEffect,gamePreferences.getInt("SOUND_FX_VOLUME", 60));
        setSeekBar(seekbarButton, gamePreferences.getInt("BUTTON_VOLUME", 90));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamePreferencesEdit = gamePreferences.edit();
                gamePreferencesEdit.putInt("MUSIC_VOLUME", seekbarMusic.getProgress());
                gamePreferencesEdit.putInt("SOUND_FX_VOLUME", seekbarEffect.getProgress());
                gamePreferencesEdit.putInt("BUTTON_VOLUME", seekbarButton.getProgress());
                gamePreferencesEdit.apply();
                gf.playBubble();
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gamePreferencesEdit = gamePreferences.edit();
        gamePreferencesEdit.putInt("MUSIC_VOLUME", seekbarMusic.getProgress());
        gamePreferencesEdit.putInt("SOUND_FX_VOLUME", seekbarEffect.getProgress());
        gamePreferencesEdit.putInt("BUTTON_VOLUME", seekbarButton.getProgress());
        gamePreferencesEdit.apply();
    }

    private void setSeekBar(SeekBar seekBar, int value) {
        seekBar.setMax(100);
        seekBar.setProgress(value);
    }
}
