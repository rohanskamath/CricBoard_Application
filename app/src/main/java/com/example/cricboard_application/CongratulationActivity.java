package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class CongratulationActivity extends AppCompatActivity {

    TextView tvWinningTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        tvWinningTeam=findViewById(R.id.tvWinningTeam);
        tvWinningTeam.setText(tvWinningTeam.getText().toString().replace("Team Name",getIntent().getStringExtra("Winning Team Name")));

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