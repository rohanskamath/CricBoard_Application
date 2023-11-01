package com.example.cricboard_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ScoreboardOpponentActivity extends AppCompatActivity {

    /* UI Objects */
    Button btnRetire,btnWide,btnNoBall;
    TextView tvTeamRuns, tvTeamWickets, tvTeamOvers, tvTeamCRR,tvTarget;
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
    Batsman striker, nonStriker;
    ArrayList<Batsman> oldBatsmanList,databaseBatsmanList;
    ArrayList<Bowler> oldBowlerList,databaseBowlerList;
    ArrayList<String> StrikerList;
    ArrayAdapter newStrikerAdapter;
    String battingTeam, bowlingTeam;
    int targetRuns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_opponent);

        oldBatsmanList = new ArrayList<>();
        oldBowlerList = new ArrayList<>();
        databaseBatsmanList = new ArrayList<>();
        databaseBowlerList = new ArrayList<>();

        /* SharedPreference & DatabaseHandler Objects */
        sharedPreferences = new CricBoardSharedPreferences(this);
        dataBaseHandler = new DataBaseHandler(this);
        sharedPreferences.setTotalTeamRuns(0);
        sharedPreferences.setTotalOvers(0.0f);
        sharedPreferences.setTotalTeamWickets(0);
        sharedPreferences.setCurrentRunRate();

        battingTeam = getIntent().getStringExtra("Current Batting Team");
        bowlingTeam = getIntent().getStringExtra("Current Bowling Team");
        targetRuns = getIntent().getIntExtra("Target Runs", -1);

        getSupportActionBar().setTitle(sharedPreferences.getHostTeamName() + " v/s " + sharedPreferences.getVisitorTeamName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        /* Setting UI with java */
        tvBattingTeamName = findViewById(R.id.tvBattingTeamName);
        tvPlayerStrike = findViewById(R.id.tvPlayerStrike);
        tvPlayerNonStrike = findViewById(R.id.tvPlayerNonStrike);
        tvBowlerName = findViewById(R.id.tvBowlerName);

        btnRetire = findViewById(R.id.btnRetire);
        btnWide=findViewById(R.id.btnWide);
        btnNoBall=findViewById(R.id.btnNoBall);

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
        tvTarget=findViewById(R.id.tvTarget);

        /* Creating Objects of current players */
        bowler = new Bowler(sharedPreferences.getBowlerName());
        striker = new Batsman(sharedPreferences.getStrikerName(), true);
        nonStriker = new Batsman(sharedPreferences.getNonStrikerName(), false);

        /* Setting Batting name, Striker,Non-Striker */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getVisitorTeamName());
        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            tvBattingTeamName.setText(sharedPreferences.getHostTeamName());
        }

        tvTarget.setText(String.valueOf(targetRuns)+" ("+String.valueOf(sharedPreferences.getTotalFirstTeamOvers())+")");

        /* Setting player in textview */
        tvPlayerStrike.setText(striker.getName());
        tvPlayerNonStrike.setText(nonStriker.getName());
        tvBowlerName.setText(bowler.getName());

        btnWide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bowler.setRuns(bowler.getRuns()+1);
                updateBowler(bowler);
                updateTeamStat(1,0,0.0f);
            }
        });

        btnNoBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Create AlertDialog Builder*/
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScoreboardOpponentActivity.this);

                /* Inflate custom layout for the dialog */
                LayoutInflater inflater = LayoutInflater.from(ScoreboardOpponentActivity.this);
                View noBallView = inflater.inflate(R.layout.no_ball_layout, null);
                alertDialogBuilder.setView(noBallView);
                alertDialogBuilder.setCancelable(false);

                /* To Show the Dialog Box */
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
                Button btnDone=noBallView.findViewById(R.id.btnDone);
                Spinner spinnerNoBall=noBallView.findViewById(R.id.spinnerNoBall);

                ArrayList<String> noBallArrayList=new ArrayList<>();
                noBallArrayList.add("---- Select Runs ----");
                noBallArrayList.add("0");
                noBallArrayList.add("1");
                noBallArrayList.add("2");
                noBallArrayList.add("3");
                noBallArrayList.add("4");
                noBallArrayList.add("5");
                noBallArrayList.add("6");
                ArrayAdapter<String> noBallAdapter=new ArrayAdapter<>(ScoreboardOpponentActivity.this,R.layout.spinner_item_layout,noBallArrayList);
                spinnerNoBall.setAdapter(noBallAdapter);

                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(spinnerNoBall.getSelectedItem().toString().equalsIgnoreCase("---- Select Runs ----")){
                            Toast.makeText(ScoreboardOpponentActivity.this, "Select Runs!", Toast.LENGTH_SHORT).show();
                        }else{
                            int runs=Integer.parseInt(spinnerNoBall.getSelectedItem().toString())+1;
                            bowler.setRuns(bowler.getRuns()+runs);
                            updateBowler(bowler);
                            updateTeamStat(runs,0,0.0f);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        btnZeroRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("0", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(0, 0, 0.1f);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnOneRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("1", false);

                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(1, 0, 0.1f);
                    striker.setRuns(striker.getRuns() + 1);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns() + 1);
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
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("2", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(2, 0, 0.1f);
                    bowler.setRuns(bowler.getRuns() + 2);

                    striker.setRuns(striker.getRuns() + 2);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnThreeRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }

                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("3", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(3, 0, 0.1f);

                    striker.setRuns(striker.getRuns() + 3);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns() + 3);
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
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("4", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(4, 0, 0.1f);

                    bowler.setRuns(bowler.getRuns() + 4);
                    striker.setRuns(striker.getRuns() + 4);
                    striker.setNoFours(striker.getNoFours() + 1);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnFiveRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultBowlerOperation();
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("5", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(5, 0, 0.1f);

                    striker.setRuns(striker.getRuns() + 5);
                    striker.calculateStrikeRate();
                    striker.setOnStrike(false);
                    nonStriker.setOnStrike(true);

                    bowler.setRuns(bowler.getRuns() + 5);
                    bowler.calculateEconomyRate();

                    if (bowler.getOvers() == 0.6) {
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
                striker.setBalls(striker.getBalls() + 1);
                bowler.setOvers(bowler.getOvers() + 0.1);
                if (bowler.getOvers() == 0.6) {
                    bowler.setOvers(1.0);
                    sharedPreferences.setTotalOvers(Float.parseFloat(String.format("%.1f", bowler.getOvers())));
                    showWiningTeamActivity(sharedPreferences.getWinningTeamName());
                }
                if (isWicket()) {
                    setOverRuns("W", true);
                    updateTeamStat(0, 1, 0.1f);
                } else {
                    setOverRuns("6", false);
                    if (bowler.getOvers() > 1.0) {
                        return;
                    }
                    updateTeamStat(6, 0, 0.1f);

                    bowler.setRuns(bowler.getRuns() + 6);
                    striker.setRuns(striker.getRuns() + 6);
                    striker.setNoSix(striker.getNoSix() + 1);
                }
                updateStriker(striker);
                updateBowler(bowler);
            }
        });

        btnRetire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Create AlertDialog Builder*/
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScoreboardOpponentActivity.this);

                /* Inflate custom layout for the dialog */
                LayoutInflater inflater = LayoutInflater.from(ScoreboardOpponentActivity.this);
                View retireView = inflater.inflate(R.layout.retire_layout, null);
                alertDialogBuilder.setView(retireView);
                alertDialogBuilder.setCancelable(false);

                RadioButton rdPlayer1=retireView.findViewById(R.id.rdPlayer1);
                RadioButton rdPlayer2=retireView.findViewById(R.id.rdPlayer2);
                EditText txtNewPlayer=retireView.findViewById(R.id.txtNewPlayer);
                Button btnDone=retireView.findViewById(R.id.btnDone);

                rdPlayer1.setText(sharedPreferences.getStrikerName());
                rdPlayer2.setText(sharedPreferences.getNonStrikerName());

                /* To Show the Dialog Box */
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!txtNewPlayer.getText().toString().equals("")){
                            if(rdPlayer1.isChecked()){
                                tvPlayerStrike.setText(txtNewPlayer.getText().toString());
                                dialog.dismiss();
                            }else if(rdPlayer2.isChecked()){
                                tvPlayerNonStrike.setText(txtNewPlayer.getText().toString());
                                dialog.dismiss();
                            } else{
                                Toast.makeText(ScoreboardOpponentActivity.this, "Select the radio button to replace!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ScoreboardOpponentActivity.this, "Enter Player Name!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                databaseBowlerList.add(bowler);
                databaseBatsmanList.add(striker);
                databaseBatsmanList.add(nonStriker);
            }
        } else {
            if (bowler.getBalls() == 1) {
                tvBallOne.setText(run);
                if(run.equals("4") || run.equals("6")){
                    tvBallOne.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                }
            } else if (bowler.getBalls() == 2) {
                tvBallTwo.setText(run);
                if(run.equals("4") || run.equals("6")){
                    tvBallTwo.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                }
            } else if (bowler.getBalls() == 3) {
                tvBallThree.setText(run);
                if(run.equals("4") || run.equals("6")){
                    tvBallThree.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                }
            } else if (bowler.getBalls() == 4) {
                tvBallFour.setText(run);
                if(run.equals("4") || run.equals("6")){
                    tvBallFour.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                }
            } else if (bowler.getBalls() == 5) {
                tvBallFive.setText(run);
                if(run.equals("4") || run.equals("6")){
                    tvBallFive.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                }
            } else if (bowler.getBalls() == 6) {
                if (tvBallSix.getText().toString().equalsIgnoreCase("-1")) {
                    tvBallSix.setText(run);
                    if(run.equals("4") || run.equals("6")){
                        tvBallSix.setBackground(getResources().getDrawable(R.drawable.round_textview_default));
                    }
                }
                databaseBowlerList.add(bowler);
                databaseBatsmanList.add(striker);
                databaseBatsmanList.add(nonStriker);
                sharedPreferences.saveBatsmanList(databaseBatsmanList);
                sharedPreferences.saveBowlerList(databaseBowlerList);
            }
        }
    }

    /* Function checks if ball is wicket or not ,if wicket then save the current striker and take
    new striker as input nd store in shared preference */
    public boolean isWicket() {
        if (chkBoxWicket.isChecked()) {
            databaseBatsmanList.add(striker);
            bowler.setWickets(bowler.getWickets() + 1);
            if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                return showCustomAlertDialog(this, sharedPreferences.getHostTeamName());
            } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                return showCustomAlertDialog(this, sharedPreferences.getVisitorTeamName());
            }
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
                if (sharedPreferences.getTotalTeamRuns() <= targetRuns) {
                    showWiningTeamActivity(bowlingTeam);
                    sharedPreferences.setWinningTeamName(bowlingTeam);
                } else {
                    showWiningTeamActivity(battingTeam);
                    sharedPreferences.setWinningTeamName(battingTeam);
                }
            }
        } else {
            bowler.setOvers(bowler.getOvers() + 1);
        }
    }

    /*  Show customDialog box for new batsman after wicket */
    public boolean showCustomAlertDialog(Context context, String battingTeamName) {
        /* Create AlertDialog Builder*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        /* Inflate custom layout for the dialog */
        LayoutInflater inflater = LayoutInflater.from(context);
        View wicketView = inflater.inflate(R.layout.wicket_layout, null);
        alertDialogBuilder.setView(wicketView);
        alertDialogBuilder.setCancelable(false);

        /* Initialization */
        spinnerNewStriker = wicketView.findViewById(R.id.spinnerNewStriker);
        dataBaseHandler = new DataBaseHandler(this);
        sharedPreferences = new CricBoardSharedPreferences(this);

        /* Set array adapters according to previously chosen option */
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            /* Getting Host and Visitor player names from database */

            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getVisitorTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName());
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            for (Batsman player : oldBatsmanList) {
                StrikerList.remove(player.getName().replace("*", ""));
            }
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);

        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            StrikerList = dataBaseHandler.getPlayerNamesByTeamName(sharedPreferences.getHostTeamName());
            StrikerList.remove(sharedPreferences.getStrikerName().replace("*", ""));
            StrikerList.remove(sharedPreferences.getNonStrikerName());
            for (Batsman player : oldBatsmanList) {
                StrikerList.remove(player.getName().replace("*", ""));
            }
            if (StrikerList.size() == 2)
                return false;
            newStrikerAdapter = new ArrayAdapter(context, R.layout.spinner_item_layout, StrikerList);
            spinnerNewStriker.setAdapter(newStrikerAdapter);

        }
        /* To Show the Dialog Box */
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        if (StrikerList.size() == 1) {
            dialog.dismiss();
            if (!sharedPreferences.getIsTargetActivityDone()) {
                Intent targetIntent = new Intent(ScoreboardOpponentActivity.this, TargetActivity.class);
                if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                    targetIntent.putExtra("Next Batting Name", sharedPreferences.getVisitorTeamName());
                } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
                    targetIntent.putExtra("Next Batting Name", sharedPreferences.getHostTeamName());
                }
                targetIntent.putExtra("Total Team Runs", sharedPreferences.getTotalTeamRuns());
                targetIntent.putExtra("Total Overs", sharedPreferences.getTotalOvers());
                targetIntent.putExtra("Required Run Rate", sharedPreferences.getCurrentRunRate());
                sharedPreferences.setIsTargetActivityDone(true);
                startActivity(targetIntent);
            }

        }
        Button btnDone;
        btnDone = wicketView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerNewStriker.getSelectedItem().toString().equalsIgnoreCase("---- Select Player ----")) {
                    Toast.makeText(context, "Select new Batsman!", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.setNewStrikerName(spinnerNewStriker.getSelectedItem().toString());
                    oldBatsmanList.add(striker);
                    striker = new Batsman(spinnerNewStriker.getSelectedItem().toString(), true);
                    sharedPreferences.setStrikerName(striker.getName());
                    updateStriker(striker);
                    dialog.dismiss();
                }
            }
        });
        return true;
    }

    /* Function to update current players stat */
    public void updateStriker(Batsman striker) {
        tvPlayerStrike.setText(striker.getName());
        tvSplayerBalls.setText(String.valueOf(striker.getBalls()));
        tvSplayerRuns.setText(String.valueOf(striker.getRuns()));
        tvSplayer4s.setText(String.valueOf(striker.getNoFours()));
        tvSplayer6s.setText(String.valueOf(striker.getNoSix()));
        tvSplayerSR.setText((String.format("%.1f", striker.getStrikeRate())));
    }

    public void updateNStriker(Batsman nonStriker) {
        tvPlayerNonStrike.setText(nonStriker.getName());
        tvNSplayerBalls.setText(String.valueOf(nonStriker.getBalls()));
        tvNSplayerRuns.setText(String.valueOf(nonStriker.getRuns()));
        tvNSplayer4s.setText(String.valueOf(nonStriker.getNoFours()));
        tvNSplayer6s.setText(String.valueOf(nonStriker.getNoSix()));
        tvNSplayerSR.setText((String.format("%.1f", nonStriker.getStrikeRate())));
    }

    public void updateBowler(Bowler bowler) {
        tvOvers.setText((String.format("%.1f", bowler.getOvers())));
        tvMaiden.setText(String.valueOf(bowler.getMaidens()));
        tvBowlerRuns.setText(String.valueOf(bowler.getRuns()));
        tvBowlerWickets.setText(String.valueOf(bowler.getWickets()));
        tvER.setText((String.format("%.1f", bowler.getEconomyRate())));
    }

    /* Function to update Total Team Runs,Wickets,Overs,CRR */
    public void updateTeamStat(int runs, int wicket, float overs) {

        sharedPreferences.setTotalTeamRuns(sharedPreferences.getTotalTeamRuns() + runs);
        int playerCount = 0;
        if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            playerCount = dataBaseHandler.getPlayerCountByTeamName(sharedPreferences.getVisitorTeamName()) - 2;
        } else if ((sharedPreferences.getTossWonBy().equals(sharedPreferences.getVisitorTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Batting") || (sharedPreferences.getTossWonBy().equals(sharedPreferences.getHostTeamName()) && sharedPreferences.getOptedTo().equalsIgnoreCase("Bowling")))) {
            playerCount = dataBaseHandler.getPlayerCountByTeamName(sharedPreferences.getHostTeamName()) - 2;
        }
        if (!(playerCount < sharedPreferences.getTotalTeamWickets())) {
            sharedPreferences.setTotalTeamWickets(sharedPreferences.getTotalTeamWickets() + wicket);
        }

        if (sharedPreferences.getTotalOvers() == 0.6f) {
            sharedPreferences.setTotalOvers(1.0f);

        } else if (sharedPreferences.getTotalOvers() < 0.6f) {
            sharedPreferences.setTotalOvers(sharedPreferences.getTotalOvers() + overs);
        }
        sharedPreferences.setCurrentRunRate();
        tvTeamRuns.setText(String.valueOf(sharedPreferences.getTotalTeamRuns()));

        if (sharedPreferences.getTotalTeamRuns() >= targetRuns) {
            showWiningTeamActivity(battingTeam);
            sharedPreferences.setWinningTeamName(battingTeam);
        }
        tvTeamWickets.setText(String.valueOf(sharedPreferences.getTotalTeamWickets()));
        tvTeamOvers.setText(String.format("%.1f", (sharedPreferences.getTotalOvers())));
        tvTeamCRR.setText(String.format("%.1f", (sharedPreferences.getCurrentRunRate())));
    }

    public void showWiningTeamActivity(String winingTeam) {

        dataBaseHandler.updatePlayerRecords(sharedPreferences.getBatsmanList(),sharedPreferences.getBowlerList());

        disableTextViews();
        Handler targetHandler = new Handler();
        targetHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent targetIntent = new Intent(ScoreboardOpponentActivity.this, CongratulationActivity.class);
                targetIntent.putExtra("Winning Team Name", winingTeam);
                startActivity(targetIntent);
                finish();
            }
        }, 1000);
    }

    public void disableTextViews(){
        btnZeroRuns.setClickable(false);
        btnOneRuns.setClickable(false);
        btnTwoRuns.setClickable(false);
        btnThreeRuns.setClickable(false);
        btnFourRuns.setClickable(false);
        btnFiveRuns.setClickable(false);
        btnSixRuns.setClickable(false);
    }
}