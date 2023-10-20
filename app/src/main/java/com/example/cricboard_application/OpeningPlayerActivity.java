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

public class OpeningPlayerActivity extends AppCompatActivity {

    TextView tvHostLogo,tvAwayLogo;
    Button btnStart;
    Spinner spinnerStriker,spinnerNonStriker,spinnerBowler;
    String hostTeamName,visitorTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_player);
        getSupportActionBar().setTitle("Select Opening Players");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        hostTeamName=getIntent().getStringExtra("Team Host");
        visitorTeamName=getIntent().getStringExtra("Team Visitor");

        String hostTeam[]={
                "---- Select Players ----",
                getString(R.string.player1),
                getString(R.string.player2),
                getString(R.string.player3),
                getString(R.string.player4),
                getString(R.string.player5),
                getString(R.string.player6),
                getString(R.string.player7),
                getString(R.string.player8),
                getString(R.string.player9),
                getString(R.string.player10),
                getString(R.string.player11)
        };
        String awayTeam[]={
                "---- Select Players ----",
                getString(R.string.player12),
                getString(R.string.player13),
                getString(R.string.player14),
                getString(R.string.player15),
                getString(R.string.player16),
                getString(R.string.player17),
                getString(R.string.player18),
                getString(R.string.player19),
                getString(R.string.player20),
                getString(R.string.player21),
                getString(R.string.player22),
                getString(R.string.player23)
        };

        tvHostLogo=findViewById(R.id.tvHostLogo);
        tvAwayLogo=findViewById(R.id.tvAwayLogo);
        btnStart=findViewById(R.id.btnStart);
        spinnerStriker=findViewById(R.id.spinnerStriker);
        spinnerNonStriker=findViewById(R.id.spinnerNonStriker);
        spinnerBowler=findViewById(R.id.spinnerBowler);

        /* Generating logo by taking first letter and second letter from the team name */
        int hostRandomColor = generateRandomColor();
        GradientDrawable hostDrawable = new GradientDrawable();
        hostDrawable.setShape(GradientDrawable.OVAL);
        hostDrawable.setColor(hostRandomColor);
        String teamHostName=hostTeamName.toUpperCase();
        String teamVisitorName=visitorTeamName.toUpperCase();
        String hostInitial="";
        String visitorInitial="";
        String[] hostWords = teamHostName.split(" ");
        String[] visitorWords = teamVisitorName.split(" ");

        /* Host Logo */
        if (hostWords.length == 1) {
            if (teamHostName.length() > 2) {
                char firstLetter = teamHostName.charAt(0);
                char secondLetter = teamHostName.charAt(1);
                char thirdLetter = teamHostName.charAt(2);
                hostInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter)+String.valueOf(thirdLetter);
            } else if (teamHostName.length() == 1) {
                char firstLetter = teamHostName.charAt(0);
                hostInitial = String.valueOf(firstLetter);
            }
        } else if (hostWords.length==2) {
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
                visitorInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter)+String.valueOf(thirdLetter);
            } else if (teamVisitorName.length() == 1) {
                char firstLetter = teamVisitorName.charAt(0);
                visitorInitial = String.valueOf(firstLetter);
            }
        } else if (visitorWords.length==2) {
            char firstLetter = visitorWords[0].charAt(0);
            char secondLetter = visitorWords[1].charAt(0);
            visitorInitial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        tvAwayLogo.setText(visitorInitial);
        tvAwayLogo.setBackground(awayDrawable);

        ArrayAdapter hostTeamAdapter=new ArrayAdapter(this, R.layout.spinner_item_layout,hostTeam);
        spinnerStriker.setAdapter(hostTeamAdapter);

        ArrayAdapter awayTeamAdapter=new ArrayAdapter(this, R.layout.spinner_item_layout,awayTeam);
        spinnerNonStriker.setAdapter(awayTeamAdapter);
        spinnerBowler.setAdapter(awayTeamAdapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scoreboardIntent=new Intent(OpeningPlayerActivity.this, ScoreboardActivity.class);
                startActivity(scoreboardIntent);
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