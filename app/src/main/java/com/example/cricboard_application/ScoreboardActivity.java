package com.example.cricboard_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {

    /* UI Objects */
    Button btnRetire, btnSwapBatsman;
    TextView tvBattingTeamName, tvPlayerStrike, tvPlayerNonStrike, tvBowlerName;
    TextView btnZeroRuns, btnOneRuns, btnTwoRuns, btnThreeRuns, btnFourRuns, btnFiveRuns, btnSixRuns;
    CheckBox chkBoxWicket;

    /* sharedPreferences & DataBaseHandler objects */
    CricBoardSharedPreferences sharedPreferences;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        sharedPreferences = new CricBoardSharedPreferences(this);
        getSupportActionBar().setTitle(sharedPreferences.getHostTeamName() + " v/s " + sharedPreferences.getVisitorTeamName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        /* Setting UI with java */
        tvBattingTeamName = findViewById(R.id.tvBattingTeamName);
        tvPlayerStrike = findViewById(R.id.tvPlayerStrike);
        tvPlayerNonStrike = findViewById(R.id.tvPlayerNonStrike);
        tvBowlerName = findViewById(R.id.tvBowlerName);
        btnRetire = findViewById(R.id.btnRetire);
        btnSwapBatsman = findViewById(R.id.btnSwapBatsman);
        btnZeroRuns = findViewById(R.id.btnZeroRuns);
        btnOneRuns = findViewById(R.id.btnOneRuns);
        btnTwoRuns = findViewById(R.id.btnTwoRuns);
        btnThreeRuns = findViewById(R.id.btnThreeRuns);
        btnFourRuns = findViewById(R.id.btnFourRuns);
        btnFiveRuns = findViewById(R.id.btnFiveRuns);
        btnSixRuns = findViewById(R.id.btnSixRuns);
        chkBoxWicket = findViewById(R.id.chkBoxWicket);

        /* Setting Batting name, Striker,Non-Striker */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getHostTeamName());
        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getVisitorTeamName());
        }
        tvPlayerStrike.setText(sharedPreferences.getStrikerName() + "*");
        tvPlayerNonStrike.setText(sharedPreferences.getNonStrikerName());
        tvBowlerName.setText(sharedPreferences.getBowlerName());

        btnZeroRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isWicket();
            }
        });

        btnRetire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retireIntent = new Intent(ScoreboardActivity.this, RetireActivity.class);
                startActivity(retireIntent);
            }
        });

        btnSwapBatsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ScoreSummaryIntent = new Intent(ScoreboardActivity.this, ScoreSummaryActivity.class);
                startActivity(ScoreSummaryIntent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void isWicket() {
        if (chkBoxWicket.isChecked()) {
            if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                showCustomAlertDialog(this,sharedPreferences.getHostTeamName());
            } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                showCustomAlertDialog(this,sharedPreferences.getVisitorTeamName());
            }
        }
    }

    public static void showCustomAlertDialog(Context context, String battingTeamName) {
        /* Create AlertDialog Builder*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        /* Inflate custom layout for the dialog */
        LayoutInflater inflater = LayoutInflater.from(context);
        View wicketView = inflater.inflate(R.layout.wicket_layout, null);
        alertDialogBuilder.setView(wicketView);
        alertDialogBuilder.setCancelable(false);

        /* Declaration */
        Spinner spinnerNewStriker;
        CricBoardSharedPreferences sharedPreferences;
        DataBaseHandler dataBaseHandler=new DataBaseHandler(context.getApplicationContext());
        ArrayList<String> StrikerList;
        ArrayAdapter newStrikerAdapter;

        spinnerNewStriker = wicketView.findViewById(R.id.spinnerNewStriker);
        sharedPreferences = new CricBoardSharedPreferences(context.getApplicationContext());

        /* Set array adapters according to previously chosen option */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            /* Getting Host and Visitor player names from database */
            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getHostTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName());
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);

        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getVisitorTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName());
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);
        }

        Button btnDone;
        btnDone = wicketView.findViewById(R.id.btnDone);

        // Create the AlertDialog
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        // Set click listener for cancel button
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Dismiss the dialog on cancel button click
            }
        });
    }
}