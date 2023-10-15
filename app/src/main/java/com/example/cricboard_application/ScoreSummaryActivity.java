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
    TextView dropdown_HostArrow, dropdown_VisitorArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);
        /* Setting Action bar */
        getSupportActionBar().setTitle("Team-1 v/s Team-2");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        /* Setting UI with Java */
        dropdown_HostArrow = findViewById(R.id.dropdown_HostArrow);
        dropdown_VisitorArrow = findViewById(R.id.dropdown_VisitorArrow);

        dropdown_HostArrow.setOnClickListener(view -> {
            if (isTeamHostFragmentVisible) {

                /* If the ScoreSummaryHostFragment is already visible, remove it */
                getSupportFragmentManager().popBackStack();
                isTeamHostFragmentVisible = false;
            } else {

                /* Replace the fragment container with TeamHostFragment */
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ScoreSummaryHostFragment())
                        .addToBackStack(null)
                        .commit();
                isTeamHostFragmentVisible = true;
            }
        });

        dropdown_VisitorArrow.setOnClickListener(view -> {
            if (isTeamVisitorFragmentVisible) {

                /* If the ScoreSummaryVisitorFragment is already visible, remove it */
                getSupportFragmentManager().popBackStack();
                isTeamVisitorFragmentVisible = false;
            } else {

                /* Replace the fragment container with ScoreSummaryVisitorFragment */
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ScoreSummaryVisitorFragment())
                        .addToBackStack(null)
                        .commit();
                isTeamVisitorFragmentVisible = true;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}