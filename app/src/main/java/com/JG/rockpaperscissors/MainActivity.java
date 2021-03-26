package com.JG.rockpaperscissors;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.JG.rockpaperscissors.DB.DatabaseHelper;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
ImageView computerImage,youImage;
Button rockButton,paperButton,scissorsButton;
TextView resultText;
int time =0;
    DatabaseHelper databaseHelper;
    static boolean active = true;
    SharedPreferences sharedPreferencesNight,sharedPreferencesSave,sharedPreferencesTime;
    public static final String keyIsNightMode = "isNight";
    public static final String MyNightModePreferences = "nightPrefs";

    public static final String keyIsTime = "istime";
    public static final String timePreferences = "timePrefs";

    public static final String MySaveModePreferences = "saveModePrefs";
    public static final String keyIsSaveMode = "isSaveMode";
    public static final String keyIscomputer = "computerkey";
    public static final String keyIsyou = "youkey";
    public static final String keyIsresult = "resultkey";

    final Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesNight = getSharedPreferences(MyNightModePreferences, Context.MODE_PRIVATE);
        sharedPreferencesSave = getSharedPreferences(MySaveModePreferences, Context.MODE_PRIVATE);
        sharedPreferencesTime = getSharedPreferences(timePreferences,Context.MODE_PRIVATE);

        computerImage=findViewById(R.id.computerImage);
        youImage=findViewById(R.id.youImage);
        rockButton=findViewById(R.id.rockButton);
        paperButton=findViewById(R.id.paperButton);
        scissorsButton=findViewById(R.id.scissorsButton);
        resultText=findViewById(R.id.resultText);
        databaseHelper = new DatabaseHelper(this);


        checkNightModeActivated();
        checkSaveModeActivated();
        startService();

        if(sharedPreferencesSave.getBoolean(keyIsSaveMode,true)){
            int[] image= {R.drawable.rock,R.drawable.paper,R.drawable.scissors};
            youImage.setImageResource(image[sharedPreferencesSave.getInt(keyIsyou,1)]);
            computerImage.setImageResource(image[sharedPreferencesSave.getInt(keyIscomputer,1)]);
            resultText.setText(sharedPreferencesSave.getString(keyIsresult,null));
        }

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youImage.setImageResource(R.drawable.rock);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .repeat(0)
                        .playOn(youImage);
                computerImage.setImageResource(0);
                Game(0);

            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youImage.setImageResource(R.drawable.paper);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .repeat(0)
                        .playOn(youImage);
                computerImage.setImageResource(0);
                Game(1);

            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youImage.setImageResource(R.drawable.scissors);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .repeat(0)
                        .playOn(youImage);
                computerImage.setImageResource(0);
                Game(2);

            }
        });
    }

    public void startService(){
        final Intent serviceIntent = new Intent(this, ExampleService.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startService(serviceIntent);
            }
        },10000);

    }







    @Override
    protected void onStart() {
        super.onStart();
        checkNightModeActivated();
        checkSaveModeActivated();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active=false;
        afterThreeSecondCheck();
    }


    private void checkNightModeActivated() {
        if(sharedPreferencesNight.getBoolean(keyIsNightMode,false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    public void checkSaveModeActivated(){
        if(sharedPreferencesSave.getBoolean(keyIsSaveMode,false)){
            int[] image= {R.drawable.rock,R.drawable.paper,R.drawable.scissors};
            youImage.setImageResource(image[sharedPreferencesSave.getInt(keyIsyou,1)]);
            computerImage.setImageResource(image[sharedPreferencesSave.getInt(keyIscomputer,1)]);
            resultText.setText(sharedPreferencesSave.getString(keyIsresult,null));

        }
    }


    public void Game(final int i){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int computer = new Random().nextInt(3);



                int[] image= {R.drawable.rock,R.drawable.paper,R.drawable.scissors};
                computerImage.setImageResource(image[computer]);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .repeat(0)
                        .playOn(computerImage);

                if(i==computer){
                    resultText.setText("It's a tie!");
                    databaseHelper.addData( (Integer)Math.round(System.currentTimeMillis()/1000),0,0,1);
                    time= Math.round(System.currentTimeMillis()/1000);


                }
                else if(i==0&&computer==2){
                    resultText.setText("You Win!");
                    databaseHelper.addData( (Integer)Math.round(System.currentTimeMillis()/1000),1,0,0);
                    time= Math.round(System.currentTimeMillis()/1000);
                }
                else if(i==1&&computer==0){
                    resultText.setText("You Win!");
                    databaseHelper.addData( (Integer)Math.round(System.currentTimeMillis()/1000),1,0,0);
                    time= Math.round(System.currentTimeMillis()/1000);
                }
                else if(i==2&&computer==1){
                    resultText.setText("You Win!");
                    databaseHelper.addData( (Integer)Math.round(System.currentTimeMillis()/1000),1,0,0);
                    time= Math.round(System.currentTimeMillis()/1000);
                }
                else {
                    resultText.setText("The Computer Win!");
                    databaseHelper.addData( (Integer)Math.round(System.currentTimeMillis()/1000),0,1,0);
                    time= Math.round(System.currentTimeMillis()/1000);
                }

                if(sharedPreferencesSave.getBoolean(keyIsSaveMode,true)){
                    SharedPreferences.Editor editor = sharedPreferencesSave.edit();
                    editor.putInt(keyIscomputer,computer);
                    editor.putInt(keyIsyou,i);
                    editor.putString(keyIsresult,resultText.getText().toString());
                    editor.apply();
                }
                if(time!=0){
                        SharedPreferences.Editor editor = sharedPreferencesTime.edit();
                        editor.putInt(keyIsTime,time);
                        editor.apply();
                    }

            }
        }, 1000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings: startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            return true;
            case R.id.statistics:
                startActivity(new Intent(MainActivity.this,StatisticActivity.class));
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


public void afterThreeSecondCheck(){
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "Keep on Playing! You know you want to!", Toast.LENGTH_SHORT).show();
        }
},3000);
    }



}