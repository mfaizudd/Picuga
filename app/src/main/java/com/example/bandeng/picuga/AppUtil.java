package com.example.bandeng.picuga;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
