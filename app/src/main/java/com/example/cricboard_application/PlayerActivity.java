package com.example.cricboard_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements PlayerInterfaceRV {

    /* UI Object */
    RecyclerView playerRecyclerView;
    FloatingActionButton addPlayerFloat;

    ArrayList<PlayerNames> playerArrayList;
    String playerNames[];

    /* Making teamName globally accessible */
    Intent TeamNameintent;
    String teamName;
    int teamID;

    /* DatabseHandler & PlayerAdapter Objects */
    DataBaseHandler dataBaseHandler;
    PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        /* Getting Team name and Team ID */
        TeamNameintent = getIntent();
        teamName = TeamNameintent.getStringExtra("Team Name");
        teamID = TeamNameintent.getIntExtra("Team ID", -1);

        /* Changing Action bar programmatically */
        getSupportActionBar().setTitle(teamName);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Database Object */
        dataBaseHandler = new DataBaseHandler(this);
        /* Setting UI with Java */
        playerRecyclerView = findViewById(R.id.playerRV);
        addPlayerFloat = findViewById(R.id.floatPlayerBtn);

        /* Setting Adapter to Recycler View */
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playerRecyclerView.setHasFixedSize(true);
        playerArrayList = dataBaseHandler.getAllPlayersByTeam(teamID);
        playerAdapter = new PlayerAdapter(this, playerArrayList, getSupportFragmentManager(), this);
        playerRecyclerView.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();

        addPlayerFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPlayerIntent = new Intent(PlayerActivity.this, AddPlayerActivity.class);
                addPlayerIntent.putExtra("Team ID", teamID);
                startActivity(addPlayerIntent);
            }
        });

    }

    /* To Go back to Previous activity and Refresh / to get updated Data */
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    /* Navigate to Back in actionbar */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    /* Onclick of Single recyclerview */
    @Override
    public void onItemClick(int position) {
        Intent playerStatIntent = new Intent(this, PlayerStatActivity.class);
        playerStatIntent.putExtra("Player Name", playerArrayList.get(position).getPlayerName());
        playerStatIntent.putExtra("Player ID", playerArrayList.get(position).getPlayerId());
        startActivity(playerStatIntent);
    }
}