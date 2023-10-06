package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cricboard_application.databinding.ActivityMainBinding;
import com.example.cricboard_application.databinding.ActivityPlayerStatBinding;

public class PlayerStatActivity extends AppCompatActivity {

    TextView tvPlayerName,tvBatting,tvBowling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stat);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        replaceFragment(new BattingFragment());

        tvPlayerName=findViewById(R.id.tvPlayerName);
        tvBatting=findViewById(R.id.tvBatting);
        tvBowling=findViewById(R.id.tvBowling);

        tvPlayerName.setText(getIntent().getStringExtra("Player Name"));
        tvBatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new BattingFragment());
            }
        });
        tvBowling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new BowlingFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.playerStatLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}