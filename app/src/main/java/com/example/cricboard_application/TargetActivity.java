
package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity {

    TextView tvTeamTarget,tvRunrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));

        tvTeamTarget=findViewById(R.id.tvTeamTarget);
        tvRunrate=findViewById(R.id.tvRunrate);

        tvTeamTarget.setText(getIntent().getStringExtra("Next Batting Name")+" needs "+(getIntent().getIntExtra("Total Team Runs",-1)+1)+" runs in "+getIntent().getFloatExtra("Total Overs",-1)+" overs");
        tvRunrate.setText("Required Run Rate:"+getIntent().getFloatExtra("Required Run Rate",-1));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }
}