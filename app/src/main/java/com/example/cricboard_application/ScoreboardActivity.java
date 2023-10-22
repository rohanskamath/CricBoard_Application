package com.example.cricboard_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {

    /* UI Objects */
    Button btnRetire, btnSwapBatsman;
    TextView tvTeamRuns,tvTeamWickets,tvTeamOvers,tvTeamCRR;
    TextView tvBattingTeamName, tvPlayerStrike, tvPlayerNonStrike, tvBowlerName;
    TextView btnZeroRuns, btnOneRuns, btnTwoRuns, btnThreeRuns, btnFourRuns, btnFiveRuns, btnSixRuns;
    TextView tvBallOne, tvBallTwo, tvBallThree, tvBallFour, tvBallFive, tvBallSix;
    TextView tvSplayerRuns, tvSplayerBalls, tvSplayer4s, tvSplayer6s, tvSplayerSR, tvNSplayerRuns, tvNSplayerBalls, tvNSplayer4s, tvNSplayer6s, tvNSplayerSR, tvOvers, tvMaiden, tvBowlerRuns, tvBowlerWickets, tvER;
    CheckBox chkBoxWicket;
    Spinner spinnerNewStriker;

    /* sharedPreferences & DataBaseHandler objects */
    CricBoardSharedPreferences sharedPreferences;
    DataBaseHandler dataBaseHandler;

    /* Objects */
    Bowler bowler;
    Batsman striker,nonStriker;
    ArrayList<Batsman> oldBatsmanList;
    ArrayList<Bowler> oldBowlerList;
    ArrayList<String> StrikerList;
    ArrayAdapter newStrikerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        oldBatsmanList=new ArrayList<>();
        oldBowlerList=new ArrayList<>();
        /* SharedPreference & DatabaseHandler Objects */
        sharedPreferences = new CricBoardSharedPreferences(this);
        sharedPreferences.setTotalTeamRuns(0);
        sharedPreferences.setTotalOvers(0.0f);
        sharedPreferences.setTotalTeamWickets(0);
        sharedPreferences.setCurrentRunRate();

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

        tvBallOne = findViewById(R.id.tvBallOne);
        tvBallTwo = findViewById(R.id.tvBallTwo);
        tvBallThree = findViewById(R.id.tvBallThree);
        tvBallFour = findViewById(R.id.tvBallFour);
        tvBallFive = findViewById(R.id.tvBallFive);
        tvBallSix = findViewById(R.id.tvBallSix);

        tvSplayerRuns = findViewById(R.id.tvSplayerRuns);
        tvSplayerBalls = findViewById(R.id.tvSplayerBalls);
        tvSplayer4s = findViewById(R.id.tvSplayer4s);
        tvSplayer6s = findViewById(R.id.tvSplayer6s);
        tvSplayerSR = findViewById(R.id.tvSplayerSR);

        tvNSplayerRuns = findViewById(R.id.tvNSplayerRuns);
        tvNSplayerBalls = findViewById(R.id.tvNSplayerBalls);
        tvNSplayer4s = findViewById(R.id.tvNSplayer4s);
        tvNSplayer6s = findViewById(R.id.tvNSplayer6s);
        tvNSplayerSR = findViewById(R.id.tvNSplayerSR);

        tvOvers = findViewById(R.id.tvOvers);
        tvMaiden = findViewById(R.id.tvMaiden);
        tvBowlerRuns = findViewById(R.id.tvBowlerRuns);
        tvBowlerWickets = findViewById(R.id.tvBowlerWickets);
        tvER = findViewById(R.id.tvER);

        tvTeamRuns = findViewById(R.id.tvTeamRuns);
        tvTeamWickets = findViewById(R.id.tvTeamWickets);
        tvTeamOvers = findViewById(R.id.tvTeamOvers);
        tvTeamCRR = findViewById(R.id.tvTeamCRR);

        /* Creating Objects of current players */
        bowler = new Bowler(sharedPreferences.getBowlerName());
        striker=new Batsman(sharedPreferences.getStrikerName(),true);
        nonStriker=new Batsman(sharedPreferences.getNonStrikerName(),false);

        /* Setting Batting name, Striker,Non-Striker */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getHostTeamName());
        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getVisitorTeamName());
        }

        /* Setting player in textview */
        tvPlayerStrike.setText(striker.getName());
        tvPlayerNonStrike.setText(nonStriker.getName());
        tvBowlerName.setText(bowler.getName());

        btnZeroRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("0", false);
                    updateTeamStat(0,0,0.1f);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnOneRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("1", false);

                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(1,0,0.1f);
                    striker.setRuns(striker.getRuns()+1);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns()+1);
                    bowler.calculateEconomyRate();

                    Batsman tempNonStriker = striker.getObject();
                    Batsman tempStriker = nonStriker.getObject();
                    striker = tempStriker;
                    nonStriker = tempNonStriker;

                    updateNStriker(nonStriker);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnTwoRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("2", false);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(2,0,0.1f);
                    bowler.setRuns(bowler.getRuns()+2);

                    striker.setRuns(striker.getRuns()+2);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnThreeRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }

                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("3", false);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(3,0,0.1f);

                    striker.setRuns(striker.getRuns()+3);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns()+3);
                    bowler.calculateEconomyRate();

                    Batsman tempNonStriker = striker.getObject();
                    Batsman tempStriker = nonStriker.getObject();
                    striker = tempStriker;
                    nonStriker = tempNonStriker;

                    updateNStriker(nonStriker);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnFourRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("4", false);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(4,0,0.1f);

                    bowler.setRuns(bowler.getRuns()+4);
                    striker.setRuns(striker.getRuns()+4);
                    striker.setNoFours(striker.getNoFours()+1);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnFiveRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("5", false);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(5,0,0.1f);

                    striker.setRuns(striker.getRuns()+5);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns()+5);
                    bowler.calculateEconomyRate();

                    if(bowler.getOvers()==0.6){
                        bowler.setOvers(1.0);
                    }
                    Batsman tempNonStriker = striker.getObject();
                    Batsman tempStriker = nonStriker.getObject();
                    striker = tempStriker;
                    nonStriker = tempNonStriker;

                    updateNStriker(nonStriker);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });


        btnSixRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls()+1);
                bowler.setOvers(bowler.getOvers()+0.1 );
                if(bowler.getOvers()==0.6){
                    bowler.setOvers(1.0);
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0,1,0.1f);
                } else {
                    setOverRuns("6", false);
                    if(bowler.getOvers()>1.0){
                        return;
                    }
                    updateTeamStat(6,0,0.1f);

                    bowler.setRuns(bowler.getRuns()+6);
                    striker.setRuns(striker.getRuns()+6);
                    striker.setNoSix(striker.getNoSix()+1);
                }
                updateStriker(striker);
                updateBowler(bowler);
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

    /* Function checks if its wicket then we set it to w otherwise run is taken accordingly in the arguement */
    public void setOverRuns(String run, boolean isWicket) {
        if (isWicket) {
            run = "W";
            if (bowler.getBalls() == 1) {
                tvBallOne.setText(run);
                tvBallOne.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
            } else if (bowler.getBalls() == 2) {
                tvBallTwo.setText(run);
                tvBallTwo.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
            } else if (bowler.getBalls() == 3) {
                tvBallThree.setText(run);
                tvBallThree.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
            } else if (bowler.getBalls() == 4) {
                tvBallFour.setText(run);
                tvBallFour.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
            } else if (bowler.getBalls() == 5) {
                tvBallFive.setText(run);
                tvBallFive.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
            } else if (bowler.getBalls() == 6) {
                if (tvBallSix.getText().toString().equalsIgnoreCase("-1")) {
                    tvBallSix.setText(run);
                    tvBallSix.setBackground(getResources().getDrawable(R.drawable.round_textview_out_outline));
                }
            }
        } else {
            if (bowler.getBalls() == 1) {
                tvBallOne.setText(run);
            } else if (bowler.getBalls() == 2) {
                tvBallTwo.setText(run);
            } else if (bowler.getBalls() == 3) {
                tvBallThree.setText(run);
            } else if (bowler.getBalls() == 4) {
                tvBallFour.setText(run);
            } else if (bowler.getBalls() == 5) {
                tvBallFive.setText(run);
            } else if (bowler.getBalls() == 6) {
                if (tvBallSix.getText().toString().equalsIgnoreCase("-1")) {
                    tvBallSix.setText(run);
                }
            }
        }
    }

    /* Function checks if ball is wicket or not ,if wicket then save the current striker and take
    new striker as input nd store in shared preference */
    public boolean isWicket() {
        if (chkBoxWicket.isChecked()) {
            bowler.setWickets(bowler.getWickets() + 1);
            if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                showCustomAlertDialog(this, sharedPreferences.getHostTeamName());
            } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                showCustomAlertDialog(this, sharedPreferences.getVisitorTeamName());
            }
            return true;
        }
        return false;
    }

    /* Default bowler functionality */
    public void defaultBowlerOperation() {
        if (bowler.getBalls() < 6) {
            bowler.setBalls(bowler.getBalls() + 1);
            if (bowler.getBalls() == 1) {
                tvBallOne.setVisibility(View.VISIBLE);
            } else if (bowler.getBalls() == 2) {
                tvBallTwo.setVisibility(View.VISIBLE);
            } else if (bowler.getBalls() == 3) {
                tvBallThree.setVisibility(View.VISIBLE);
            } else if (bowler.getBalls() == 4) {
                tvBallFour.setVisibility(View.VISIBLE);
            } else if (bowler.getBalls() == 5) {
                tvBallFive.setVisibility(View.VISIBLE);
            } else if (bowler.getBalls() == 6) {
                tvBallSix.setVisibility(View.VISIBLE);
            }
        } else {
            bowler.setOvers(bowler.getOvers() + 1);
        }
    }

    /*  Show customDialog box for new batsman after wicket */
    public void showCustomAlertDialog(Context context, String battingTeamName) {
        /* Create AlertDialog Builder*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        /* Inflate custom layout for the dialog */
        LayoutInflater inflater = LayoutInflater.from(context);
        View wicketView = inflater.inflate(R.layout.wicket_layout, null);
        alertDialogBuilder.setView(wicketView);
        alertDialogBuilder.setCancelable(false);

        /* Initialization */
        spinnerNewStriker = wicketView.findViewById(R.id.spinnerNewStriker);
        dataBaseHandler=new DataBaseHandler(this);
        sharedPreferences=new CricBoardSharedPreferences(this);

        /* Set array adapters according to previously chosen option */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            /* Getting Host and Visitor player names from database */
            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getHostTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName().replace("*",""));
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            for(Batsman player: oldBatsmanList){
                StrikerList.remove(player.getName().replace("*",""));
            }
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);

        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getVisitorTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName());
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            for(Batsman player: oldBatsmanList){
                StrikerList.remove(player.getName().replace("*",""));
            }
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);
        }
        /* To Show the Dialog Box */
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        if (StrikerList.size()==1){
            dialog.dismiss();
            Intent targetIntent = new Intent(ScoreboardActivity.this, TargetActivity.class);
            if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                targetIntent.putExtra("Next Batting Name",sharedPreferences.getVisitorTeamName());
            } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                targetIntent.putExtra("Next Batting Name",sharedPreferences.getHostTeamName());
            }
            targetIntent.putExtra("Total Team Runs",sharedPreferences.getTotalTeamRuns());
            targetIntent.putExtra("Total Overs",sharedPreferences.getTotalOvers());
            targetIntent.putExtra("Required Run Rate",sharedPreferences.getCurrentRunRate());
            startActivity(targetIntent);
        }
        Button btnDone;
        btnDone = wicketView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerNewStriker.getSelectedItem().toString().equalsIgnoreCase("---- Select Player ----")){
                    Toast.makeText(context, "Select new Batsman!", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.setNewStrikerName(spinnerNewStriker.getSelectedItem().toString());
                    oldBatsmanList.add(striker);
                    striker=new Batsman(spinnerNewStriker.getSelectedItem().toString(),true);
                    sharedPreferences.setStrikerName(striker.getName());
                    updateStriker(striker);
                    dialog.dismiss();
                }

            }
        });
    }

    /* Function to update current players stat */
    public void updateStriker(Batsman striker){
        tvPlayerStrike.setText(striker.getName());
        tvSplayerBalls.setText(String.valueOf(striker.getBalls()));
        tvSplayerRuns.setText(String.valueOf(striker.getRuns()));
        tvSplayer4s.setText(String.valueOf(striker.getNoFours()));
        tvSplayer6s.setText(String.valueOf(striker.getNoSix()));
        tvSplayerSR.setText((String.format("%.1f",striker.getStrikeRate())));
    }
    public void updateNStriker(Batsman nonStriker){
        tvPlayerNonStrike.setText(nonStriker.getName());
        tvNSplayerBalls.setText(String.valueOf(nonStriker.getBalls()));
        tvNSplayerRuns.setText(String.valueOf(nonStriker.getRuns()));
        tvNSplayer4s.setText(String.valueOf(nonStriker.getNoFours()));
        tvNSplayer6s.setText(String.valueOf(nonStriker.getNoSix()));
        tvNSplayerSR.setText((String.format("%.1f",nonStriker.getStrikeRate())));
    }

    public void updateBowler(Bowler bowler){
        tvOvers.setText((String.format("%.1f",bowler.getOvers())));
        tvMaiden.setText(String.valueOf(bowler.getMaidens()));
        tvBowlerRuns.setText(String.valueOf(bowler.getRuns()));
        tvBowlerWickets.setText(String.valueOf(bowler.getWickets()));
        tvER.setText((String.format("%.1f",bowler.getEconomyRate())));
    }

    /* Function to update Total Team Runs,Wickets,Overs,CRR */
    public void updateTeamStat(int runs,int wicket,float overs){
        sharedPreferences.setTotalTeamRuns(sharedPreferences.getTotalTeamRuns()+runs);
        sharedPreferences.setTotalTeamWickets(sharedPreferences.getTotalTeamWickets()+wicket);
        sharedPreferences.setTotalOvers(sharedPreferences.getTotalOvers()+overs);
        if(sharedPreferences.getTotalOvers()==0.6f){
            sharedPreferences.setTotalOvers(1.0f);
        }else if(sharedPreferences.getTotalOvers()==1.6f){
            sharedPreferences.setTotalOvers(2.0f);
        }
        sharedPreferences.setCurrentRunRate();
        tvTeamRuns.setText(String.valueOf(sharedPreferences.getTotalTeamRuns()));
        tvTeamWickets.setText(String.valueOf(sharedPreferences.getTotalTeamWickets()));
        tvTeamOvers.setText(String.format("%.1f",(sharedPreferences.getTotalOvers())));
        tvTeamCRR.setText(String.format("%.1f",(sharedPreferences.getCurrentRunRate())));
    }
}