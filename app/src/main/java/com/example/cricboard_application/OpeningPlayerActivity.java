package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OpeningPlayerActivity extends AppCompatActivity {

    /* UI Objects */
    TextView tvHostLogo, tvAwayLogo;
    Button btnStart;
    Spinner spinnerStriker, spinnerNonStriker, spinnerBowler;

    String hostTeamName, visitorTeamName;

    /* DataBaseHandler, Shared Preference ,ArrayList Objects */
    CricBoardSharedPreferences sharedPreferences;
    DataBaseHandler dataBaseHandler;
    ArrayList<String> hostPlayers;
    ArrayList<String> visitorPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_player);
        getSupportActionBar().setTitle("Select Opening Players");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        /* Getting next batting team,bowling team from Intent */
        hostTeamName = getIntent().getStringExtra("Team Host");
        visitorTeamName = getIntent().getStringExtra("Team Visitor");

        /* Shared preference & DatabaseHandler Object */
        sharedPreferences = new CricBoardSharedPreferences(this);
        dataBaseHandler = new DataBaseHandler(this);

        /* Setting UI with Java */
        tvHostLogo = findViewById(R.id.tvHostLogo);
        tvAwayLogo = findViewById(R.id.tvAwayLogo);
        btnStart = findViewById(R.id.btnStart);
        spinnerStriker = findViewById(R.id.spinnerStriker);
        spinnerNonStriker = findViewById(R.id.spinnerNonStriker);
        spinnerBowler = findViewById(R.id.spinnerBowler);

        /* Generating logo by taking first letter and second letter from the team name */
        int hostRandomColor = generateRandomColor();
        GradientDrawable hostDrawable = new GradientDrawable();
        hostDrawable.setShape(GradientDrawable.OVAL);
        hostDrawable.setColor(hostRandomColor);
        String teamHostName = hostTeamName.toUpperCase();
        String teamVisitorName = visitorTeamName.toUpperCase();
        String hostInitial = "";
        String visitorInitial = "";
        String[] hostWords = teamHostName.split(" ");
        String[] visitorWords = teamVisitorName.split(" ");

        /* Host Logo */
        if (hostWords.length == 1) {
            if (teamHostName.length() > 2) {
                char firstLetter = teamHostName.charAt(0);
                char secondLetter = teamHostName.charAt(1);
                char thirdLetter = teamHostName.charAt(2);
                hostInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter) + String.valueOf(thirdLetter);
            } else if (teamHostName.length() == 1) {
                char firstLetter = teamHostName.charAt(0);
                hostInitial = String.valueOf(firstLetter);
            }
        } else if (hostWords.length == 2) {
            char firstLetter = hostWords[0].charAt(0);
            char secondLetter = hostWords[1].charAt(0);
            hostInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        tvHostLogo.setText(hostInitial);
        tvHostLogo.setBackground(hostDrawable);

        /* Visitor Logo */
        int awayRandomColor = generateRandomColor();
        GradientDrawable awayDrawable = new GradientDrawable();
        awayDrawable.setShape(GradientDrawable.OVAL);
        awayDrawable.setColor(awayRandomColor);
        if (visitorWords.length == 1) {
            if (teamVisitorName.length() > 2) {
                char firstLetter = teamVisitorName.charAt(0);
                char secondLetter = teamVisitorName.charAt(1);
                char thirdLetter = teamVisitorName.charAt(2);
                visitorInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter) + String.valueOf(thirdLetter);
            } else if (teamVisitorName.length() == 1) {
                char firstLetter = teamVisitorName.charAt(0);
                visitorInitial = String.valueOf(firstLetter);
            }
        } else if (visitorWords.length == 2) {
            char firstLetter = visitorWords[0].charAt(0);
            char secondLetter = visitorWords[1].charAt(0);
            visitorInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        tvAwayLogo.setText(visitorInitial);
        tvAwayLogo.setBackground(awayDrawable);

        /* Getting Host and Visitor player names from database */
        hostPlayers = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getHostTeamName());
        visitorPlayers = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getVisitorTeamName());

        ArrayAdapter hostTeamAdapter = new ArrayAdapter(this, R.layout.spinner_item_layout, hostPlayers);
        ArrayAdapter awayTeamAdapter = new ArrayAdapter(this, R.layout.spinner_item_layout, visitorPlayers);

        /* Set array adapters according to previously chosen option */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            spinnerStriker.setAdapter(hostTeamAdapter);
            spinnerNonStriker.setAdapter(hostTeamAdapter);
            spinnerBowler.setAdapter(awayTeamAdapter);
        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            spinnerStriker.setAdapter(awayTeamAdapter);
            spinnerNonStriker.setAdapter(awayTeamAdapter);
            spinnerBowler.setAdapter(hostTeamAdapter);
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerStriker.getSelectedItem().toString().equalsIgnoreCase(spinnerNonStriker.getSelectedItem().toString())) {
                    Toast.makeText(OpeningPlayerActivity.this, "Players should not be same!", Toast.LENGTH_SHORT).show();
                } else if (spinnerStriker.getSelectedItem().toString().equalsIgnoreCase("---- Select Player ----") || spinnerNonStriker.getSelectedItem().toString().equalsIgnoreCase("---- Select Player ----") || spinnerBowler.getSelectedItem().toString().equalsIgnoreCase("---- Select Player ----")) {
                    Toast.makeText(OpeningPlayerActivity.this, "Select players!", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.setStrikerName(spinnerStriker.getSelectedItem().toString());
                    sharedPreferences.setNonStrikerName(spinnerNonStriker.getSelectedItem().toString());
                    sharedPreferences.setBowlerName(spinnerBowler.getSelectedItem().toString());

                    Intent scoreboardIntent = new Intent(OpeningPlayerActivity.this, ScoreboardActivity.class);
                    startActivity(scoreboardIntent);
                }
            }
        });
    }

    /* Generating random colors for logo */
    private int generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return Color.rgb(red, green, blue);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}