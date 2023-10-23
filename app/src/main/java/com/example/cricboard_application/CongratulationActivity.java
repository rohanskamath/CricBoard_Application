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

    TextView tvWinningTeam;
    ArrayList<History> matchHistory;
    CricBoardSharedPreferences sharedPreferences;
    DataBaseHandler dataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        dataBaseHandler=new DataBaseHandler(this);
        tvWinningTeam=findViewById(R.id.tvWinningTeam);
        tvWinningTeam.setText(tvWinningTeam.getText().toString().replace("Team Name",getIntent().getStringExtra("Winning Team Name")));

        sharedPreferences=new CricBoardSharedPreferences(this);

        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        // Format the date if needed
        DateTimeFormatter formatterDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = currentDate.format(formatterDate);
        }

        LocalTime currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now();
        }

        // Format the time if needed
        DateTimeFormatter formatterTime = null;
        String formattedTime = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             formattedTime = currentTime.format(formatterTime);
        }

        String hostTeamName=sharedPreferences.getHostTeamName();
        String visitorTeamName=sharedPreferences.getVisitorTeamName();
        String winningTeamName= sharedPreferences.getWinningTeamName();

        int firstTeamScore=sharedPreferences.getTotalFirstTeamRuns();
        int secondTeamScore=sharedPreferences.getTotalTeamRuns();

        int firstTeamWickets=sharedPreferences.getTotalFirstTeamWickets();
        int secondTeamWickets=sharedPreferences.getTotalTeamWickets();

        float firstTeamOvers=sharedPreferences.getTotalFirstTeamOvers();
        float secondTeamOvers=sharedPreferences.getTotalOvers();

        matchHistory=new ArrayList<>( );
        matchHistory.add(new History(formattedDate ,formattedTime,hostTeamName,firstTeamOvers,firstTeamScore,firstTeamWickets,
                visitorTeamName,secondTeamOvers,secondTeamScore,secondTeamWickets,winningTeamName));

        dataBaseHandler.addHistory(matchHistory.get(0));
        sharedPreferences.saveObjectList(matchHistory);

        Handler targetHandler = new Handler();
        targetHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(CongratulationActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, 3000);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}