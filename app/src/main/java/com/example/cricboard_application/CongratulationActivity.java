package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CongratulationActivity extends AppCompatActivity {

    /* UI Objects */
    TextView tvWinningTeam;
    ArrayList<History> matchHistory;

    /* DatabaseHandler and shared preference Object */
    CricBoardSharedPreferences sharedPreferences;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        /* Initialize DataBaseHandler and Shared Preference objects */
        dataBaseHandler=new DataBaseHandler(this);
        sharedPreferences=new CricBoardSharedPreferences(this);

        /* Setting UI object with java */
        tvWinningTeam=findViewById(R.id.tvWinningTeam);

        tvWinningTeam.setText(tvWinningTeam.getText().toString().replace("Team Name",getIntent().getStringExtra("Winning Team Name"))+" Won the match");

        /* Getting system currentDate */
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        DateTimeFormatter formatterDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = currentDate.format(formatterDate);
        }

        /* Getting system currentTime */
        LocalTime currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now();
        }
        DateTimeFormatter formatterTime = null;
        String formattedTime = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             formattedTime = currentTime.format(formatterTime);
        }

        /* Getting Overall match details from shared preference */
        String hostTeamName=sharedPreferences.getHostTeamName();
        String visitorTeamName=sharedPreferences.getVisitorTeamName();
        String winningTeamName= sharedPreferences.getWinningTeamName();
        int firstTeamScore=sharedPreferences.getTotalFirstTeamRuns();
        int secondTeamScore=sharedPreferences.getTotalTeamRuns();
        int firstTeamWickets=sharedPreferences.getTotalFirstTeamWickets();
        int secondTeamWickets=sharedPreferences.getTotalTeamWickets();
        float firstTeamOvers=sharedPreferences.getTotalFirstTeamOvers();
        float secondTeamOvers=sharedPreferences.getTotalOvers();

        /* Storing the details in arrayList */
        matchHistory=new ArrayList<>( );
        matchHistory.add(new History(formattedDate ,formattedTime,hostTeamName,firstTeamOvers,firstTeamScore,firstTeamWickets,
                visitorTeamName,secondTeamOvers,secondTeamScore,secondTeamWickets,winningTeamName));

        /* Storing arraylist into database-handler & Shared Preference */
        dataBaseHandler.addHistory(matchHistory.get(0));
        sharedPreferences.saveObjectList(matchHistory);

        /* Automatically after 3 seconds go to Home Page after Match */
        Handler targetHandler = new Handler();
        targetHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(CongratulationActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, 5000);
    }
}