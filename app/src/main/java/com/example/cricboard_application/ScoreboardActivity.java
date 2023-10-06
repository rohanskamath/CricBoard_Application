package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreboardActivity extends AppCompatActivity {

    Button btnRetire,btnSwapBatsman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        getSupportActionBar().setTitle("Team-1 v/s Team-2");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        btnRetire=findViewById(R.id.btnRetire);
        btnSwapBatsman=findViewById(R.id.btnSwapBatsman);

        btnRetire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retireIntent=new Intent(ScoreboardActivity.this, RetireActivity.class);
                startActivity(retireIntent);
            }
        });

        btnSwapBatsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ScoreSummaryIntent=new Intent(ScoreboardActivity.this, ScoreSummaryActivity.class);
                startActivity(ScoreSummaryIntent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}