package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_player);
        getSupportActionBar().setTitle("Select Opening Players");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}