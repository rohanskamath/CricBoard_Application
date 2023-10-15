package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cricboard_application.databinding.ActivityMainBinding;
import com.example.cricboard_application.databinding.ActivityPlayerStatBinding;

import java.io.File;

public class PlayerStatActivity extends AppCompatActivity {

    TextView tvBatting,tvBowling;
    ImageView imageViewPP;
    int player_id;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stat);

        getSupportActionBar().setTitle(getIntent().getStringExtra("Player Name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        replaceFragment(new BattingFragment());

        player_id=getIntent().getIntExtra("Player ID",-1);

        tvBatting=findViewById(R.id.tvBatting);
        tvBowling=findViewById(R.id.tvBowling);
        imageViewPP=findViewById(R.id.imageViewPP);

        dataBaseHandler=new DataBaseHandler(this);

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

        PlayerNames player = dataBaseHandler.getPlayerById(player_id);
        String imagePath="";
        try{
            imagePath = player.getPlayerImgPath().substring(7);
            if (!imagePath.isEmpty()) {
                File imgFile = new File(imagePath);
                if (imgFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imageViewPP.setImageBitmap(bitmap);
                    imageViewPP.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        }
        catch (Exception e){

        }
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