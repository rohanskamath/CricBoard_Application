package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreSummaryActivity extends AppCompatActivity {

    /* Checking for visible on click */
    private boolean isTeamHostFragmentVisible = false;
    private boolean isTeamVisitorFragmentVisible = false;

    /* UI Objects */
    TextView dropdown_HostArrow, dropdown_VisitorArrow, tvTossDetails,tvHostTeamName,tvHostRunsScored,tvHostWicketsTaken,tvHostOvers;

    CricBoardSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);
        /* Setting Action bar */
        getSupportActionBar().setTitle(getIntent().getStringExtra("Host Team Name") + " v/s " + getIntent().getStringExtra("Visitor Team Name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        sharedPreferences = new CricBoardSharedPreferences(this);

        String HostName = sharedPreferences.getHostTeamName();
        String totalRuns = String.valueOf(sharedPreferences.getTotalTeamRuns());
        float totalOvers = sharedPreferences.getTotalOvers();
        String totalWickets = String.valueOf(sharedPreferences.getTotalTeamWickets());

        /* Setting UI with Java */
        dropdown_HostArrow = findViewById(R.id.dropdown_HostArrow);
        dropdown_VisitorArrow = findViewById(R.id.dropdown_VisitorArrow);
        tvTossDetails = findViewById(R.id.tvTossDetails);
        tvHostTeamName=findViewById(R.id.tvHostTeamName);
        tvHostRunsScored=findViewById(R.id.tvHostRunsScored);
        tvHostWicketsTaken=findViewById(R.id.tvHostWicketsTaken);
        tvHostOvers=findViewById(R.id.tvHostOvers);

        tvTossDetails.setText("Team "+ sharedPreferences.getHostTeamName() + " won the toss and chose to Bat");
        tvHostTeamName.setText(HostName);
        tvHostRunsScored.setText(String.valueOf(totalRuns));
        tvHostWicketsTaken.setText(String.valueOf(totalWickets));
        tvHostOvers.setText(String.valueOf(totalOvers));

        dropdown_HostArrow.setOnClickListener(view -> {
            if (isTeamHostFragmentVisible) {
                /* If the ScoreSummaryHostFragment is already visible, remove it */
                getSupportFragmentManager().popBackStack();
                isTeamHostFragmentVisible = false;
                dropdown_HostArrow.setBackground(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down_24));
            } else {
                /* Replace the fragment container with TeamHostFragment */
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ScoreSummaryHostFragment())
                        .addToBackStack(null)
                        .commit();
                isTeamHostFragmentVisible = true;
                dropdown_HostArrow.setBackground(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up_24));
            }
        });

        dropdown_VisitorArrow.setOnClickListener(view -> {
            if (isTeamVisitorFragmentVisible) {

                /* If the ScoreSummaryVisitorFragment is already visible, remove it */
                getSupportFragmentManager().popBackStack();
                isTeamVisitorFragmentVisible = false;
                dropdown_VisitorArrow.setBackground(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_down_24));
            } else {

                /* Replace the fragment container with ScoreSummaryVisitorFragment */
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ScoreSummaryVisitorFragment())
                        .addToBackStack(null)
                        .commit();
                isTeamVisitorFragmentVisible = true;
                dropdown_VisitorArrow.setBackground(getResources().getDrawable(R.drawable.baseline_keyboard_arrow_up_24));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}