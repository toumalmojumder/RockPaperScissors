package com.JG.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesNight;
    public static final String keyIsNightMode = "isNight";
    public static final String MyNightModePreferences = "nightPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            synchronized(this){
                wait(1000);
            }
        }
        catch(InterruptedException ex){
            Log.d("Error",ex.toString());
        }
        sharedPreferencesNight = getSharedPreferences(MyNightModePreferences, Context.MODE_PRIVATE);
        checkNightModeActivated();
        startActivity(new Intent(this,MainActivity.class ));
    }
    private void checkNightModeActivated() {
        if(sharedPreferencesNight.getBoolean(keyIsNightMode,false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}