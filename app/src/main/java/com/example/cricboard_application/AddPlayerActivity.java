package com.example.cricboard_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class AddPlayerActivity extends AppCompatActivity {

    /* UI Objects */
    ShapeableImageView imgViewDP;
    FloatingActionButton floatBtnAddDP;
    Button btnAddPlayer;
    EditText txtPlayerName;
    int team_id;

    /* DataBaseHandler Object */
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        /* Changing Action bar programmatically */
        getSupportActionBar().setTitle("Add Players");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        team_id=getIntent().getIntExtra("Team ID",-1);

        /* Database object initialized */
        dataBaseHandler=new DataBaseHandler(this);

        /* Setting UI elements with Java */
        imgViewDP=findViewById(R.id.imgViewAddPhoto);
        floatBtnAddDP=findViewById(R.id.floatPlayerDPBtn);
        btnAddPlayer=findViewById(R.id.btnAddPlayer);
        txtPlayerName=findViewById(R.id.txtPlayerName);

        floatBtnAddDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(AddPlayerActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Adding Player details into Table */
                try{
                    PlayerNames players;
                    if(imgViewDP.getTag()==null){
                        players=new PlayerNames(team_id,txtPlayerName.getText().toString());
                    }else{
                        players=new PlayerNames(team_id,txtPlayerName.getText().toString(), imgViewDP.getTag().toString());
                    }
                    dataBaseHandler.addPlayer(players);
                    Toast.makeText(AddPlayerActivity.this, "Added Player Successfully!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(AddPlayerActivity.this, "Error Occurred 507", Toast.LENGTH_SHORT).show();
                     e.printStackTrace();
                }
                finish();
            }
        });
    }

    /* Setting Image URI */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=data.getData();
        imgViewDP.setImageURI(uri);
        imgViewDP.setTag(uri.toString());
    }

    /* Navigate to Back in actionbar */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(0, R.anim.slide_down);
        return super.onSupportNavigateUp();
    }
}