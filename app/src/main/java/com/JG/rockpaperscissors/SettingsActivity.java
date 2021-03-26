package com.JG.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
Switch saveSwitch, darkSwitch;
SharedPreferences sharedPreferencesNight,sharedPreferencesSave;

    public static final String MyNightModePreferences = "nightPrefs";

public static final String MySaveModePreferences = "saveModePrefs";

    public static final String keyIsNightMode = "isNight";

public static final String keyIsSaveMode = "isSaveMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferencesNight = getSharedPreferences(MyNightModePreferences, Context.MODE_PRIVATE);
        sharedPreferencesSave = getSharedPreferences(MySaveModePreferences, Context.MODE_PRIVATE);
       saveSwitch = findViewById(R.id.switch1);
       darkSwitch = findViewById(R.id.switch2);
        checkNightModeActivated();
        checkSaveModeActivated();
       saveSwitch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(saveSwitch.isChecked()){
                   saveGameModeState(true);
               }
               else{
                   saveGameModeState(false);
               }

           }
       });

        darkSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(darkSwitch.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);
                    recreate();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);
                    recreate();
                }
            }

        });
    }

    private void saveGameModeState(boolean saveGameMode) {
        SharedPreferences.Editor editor = sharedPreferencesSave.edit();
        editor.putBoolean(keyIsSaveMode,saveGameMode);
        editor.apply();
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferencesNight.edit();
        editor.putBoolean(keyIsNightMode,nightMode);
        editor.apply();
    }


    public void checkNightModeActivated(){
        if(sharedPreferencesNight.getBoolean(keyIsNightMode,false)){
            darkSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            darkSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void checkSaveModeActivated(){
        if(sharedPreferencesSave.getBoolean(keyIsSaveMode,false)){
            saveSwitch.setChecked(true);

        }
        else{
            saveSwitch.setChecked(false);

        }
    }


}