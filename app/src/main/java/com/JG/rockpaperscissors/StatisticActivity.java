package com.JG.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.JG.rockpaperscissors.DB.DatabaseHelper;

public class StatisticActivity extends AppCompatActivity {
TextView lastMinuteResult,allTimeResult;
Button reset;
    SharedPreferences sharedPreferencesTime;
    public static final String keyIsTime = "istime";
    public static final String timePreferences = "timePrefs";
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        setTitle("Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);

        lastMinuteResult = findViewById(R.id.lastMinuteResult);
        allTimeResult = findViewById(R.id.allTimeResult);
        reset = findViewById(R.id.resetButton);

        sharedPreferencesTime = getSharedPreferences(timePreferences, Context.MODE_PRIVATE);

        int winint = databaseHelper.allwin();
        int lossint = databaseHelper.allloss();
        int tieint = databaseHelper.alltie();
        String win = String.valueOf(winint);
        String loss = String.valueOf(lossint);
        String tie = String.valueOf(tieint);

        int lastwinint = databaseHelper.lastMinuteWin(sharedPreferencesTime.getInt(keyIsTime,1));
        int lastlossint = databaseHelper.lastMinuteloss(sharedPreferencesTime.getInt(keyIsTime,1));
        int lasttieint = databaseHelper.lastMinutetie(sharedPreferencesTime.getInt(keyIsTime,1));

        String lastwin = String.valueOf(lastwinint);
        String lastloss = String.valueOf(lastlossint);
        String lasttie = String.valueOf(lasttieint);

        lastMinuteResult.setText(lastwin+"-"+lastloss+"-"+lasttie);
        allTimeResult.setText(win+"-"+loss+"-"+tie);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            databaseHelper.resetTable();
                lastMinuteResult.setText("0-0-0");
                allTimeResult.setText("0-0-0");

            }
        });

    }
}