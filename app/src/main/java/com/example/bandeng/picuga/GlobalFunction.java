package com.example.bandeng.picuga;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class GlobalFunction{
    Context mContext;

    SharedPreferences gamePreferences;
    MediaPlayer player;

    public GlobalFunction(Context context){
        this.mContext = context;
        this.gamePreferences = mContext.getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        int resId = mContext.getResources().getIdentifier("bubble_pop", "raw", "com.example.bandeng.picuga");
        player = MediaPlayer.create(mContext, resId);
    }

    public void playBubble() {
        int maxVolume = 100;
        final float vol = (float)(1-(Math.log(maxVolume-gamePreferences.getInt("BUTTON_VOLUME", 90))/Math.log(maxVolume)));
        player.setVolume(vol,vol);
        player.start();
    }

    public void showAlert(String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext);
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dismiss euy
            }
        });
        dlgAlert.setMessage(message);
        dlgAlert.setCancelable(true);
        dlgAlert.setTitle("Kesalahan");
        dlgAlert.show();
    }
}
