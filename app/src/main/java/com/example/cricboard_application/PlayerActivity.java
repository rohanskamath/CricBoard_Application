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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements PlayerInterfaceRV{

    RecyclerView playerRecyclerView;
    FloatingActionButton addPlayerFloat;

    ArrayList<PlayerNames> playerArrayList;
    String playerNames[];

    //Making teamName gobally accessible
    Intent TeamNameintent;
    String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        TeamNameintent = getIntent();
        teamName=TeamNameintent.getStringExtra("Team Name");
        //Changing Action bar programmatically
        getSupportActionBar().setTitle(teamName);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        playerDataInitialize();

        playerRecyclerView=findViewById(R.id.playerRV);
        addPlayerFloat=findViewById(R.id.floatPlayerBtn);

        //Fitting Adapter to Recycler View
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playerRecyclerView.setHasFixedSize(true);
        PlayerAdapter playerAdapter=new PlayerAdapter(this,playerArrayList,getSupportFragmentManager(),this);
        playerRecyclerView.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();

        addPlayerFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPlayerIntent=new Intent(PlayerActivity.this, AddPlayerActivity.class);
                startActivity(addPlayerIntent);
            }
        });

    }

    private void playerDataInitialize() {
        playerArrayList = new ArrayList<>();
        if(teamName.equalsIgnoreCase("India"))
        {
            playerNames = new String[]{
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
        } else if(teamName.equalsIgnoreCase("Sri Lanka")){
            playerNames = new String[]{
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
        } else if(teamName.equalsIgnoreCase("Australia")){
            playerNames = new String[]{
                    getString(R.string.player24),
                    getString(R.string.player25),
                    getString(R.string.player26),
                    getString(R.string.player27),
                    getString(R.string.player28),
                    getString(R.string.player29),
                    getString(R.string.player30),
                    getString(R.string.player31),
                    getString(R.string.player32),
                    getString(R.string.player33),
                    getString(R.string.player34),
                    getString(R.string.player35)
            };
        } else if(teamName.equalsIgnoreCase("New Zealand")) {
            playerNames = new String[]{
                    getString(R.string.player35),
                    getString(R.string.player36),
                    getString(R.string.player37),
                    getString(R.string.player38),
                    getString(R.string.player39),
                    getString(R.string.player40)
            };
        } else if(teamName.equalsIgnoreCase("England")) {
            playerNames = new String[]{
                    getString(R.string.player41),
                    getString(R.string.player42),
                    getString(R.string.player43),
                    getString(R.string.player44),
                    getString(R.string.player45)
            };
        } else if(teamName.equalsIgnoreCase("West Indies")) {
            playerNames = new String[]{
                    getString(R.string.player46),
                    getString(R.string.player47),
                    getString(R.string.player48),
                    getString(R.string.player49),
                    getString(R.string.player50)
            };
        }
        else
        {
            playerNames = new String[]{
                    getString(R.string.player0)
            };
        }

        for(int i=0;i<playerNames.length;i++)
        {
            PlayerNames players=new PlayerNames(playerNames[i]);
            playerArrayList.add(players);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(int position) {
        Intent playerStatIntent=new Intent(this, PlayerStatActivity.class);
        playerStatIntent.putExtra("Player Name",playerNames[position]);
        startActivity(playerStatIntent);
    }
}