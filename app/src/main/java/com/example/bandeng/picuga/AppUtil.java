package com.example.bandeng.picuga;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Bandeng on 7/21/2018.
 */

public class AppUtil {

    public static JSONObject readJSON(int id, Context context) {
        JSONObject result = null;
        try {
            InputStream stream = context.getResources().openRawResource(id);
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

    public static void restartInLocale(Context context, Locale locale)
    {
        SharedPreferences gamePreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        ((Activity)context).recreate();
        SharedPreferences.Editor edit = gamePreferences.edit();
        edit.putString("GAME_LANG", locale.getDisplayLanguage());
        edit.apply();
    }

    public static void setLocale(Context context, Locale locale)
    {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public static void playMusic(Context context, String asset, boolean loop) throws IOException {
        MediaPlayer player = new MediaPlayer();
        AssetFileDescriptor afd = context.getAssets().openFd(asset);
        SharedPreferences gamePreferences = context.getSharedPreferences("com.example.bandeng.picuga", MODE_PRIVATE);
        int maxVolume = 100;
        final float vol = (float)(1-(Math.log(maxVolume-gamePreferences.getInt("BUTTON_VOLUME", 90))/Math.log(maxVolume)));
        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        player.setVolume(vol,vol);
        player.setLooping(loop);
        player.start();
    }
}
