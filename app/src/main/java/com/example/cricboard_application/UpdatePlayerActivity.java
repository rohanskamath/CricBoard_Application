package com.example.cricboard_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import java.io.File;

public class UpdatePlayerActivity extends AppCompatActivity {

    /* UI Objects */
    ShapeableImageView imgViewDP;
    FloatingActionButton floatBtnUpdateDP;
    Button btnUpdatePlayer;
    EditText txtPlayerName;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        /* Changing Action bar programmatically */
        getSupportActionBar().setTitle("Update Players");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Setting UI Objects with Java */
        imgViewDP = findViewById(R.id.imgViewUpdatePhoto);
        floatBtnUpdateDP = findViewById(R.id.floatPlayerDPBtn);
        btnUpdatePlayer = findViewById(R.id.btnUpdatePlayer);
        txtPlayerName = findViewById(R.id.txtPlayerName);

        /* Database-handler object created */
        dataBaseHandler = new DataBaseHandler(this);

        /* Getting values from previous Activity */
        String playerName = getIntent().getStringExtra("Player Name");
        int playerId = getIntent().getIntExtra("Player ID", -1);
        txtPlayerName.setText(playerName);

        /* Setting Image path from database and loading into image view */
        PlayerNames player = dataBaseHandler.getPlayerById(playerId);
        String imagePath = "";
        try {
            imagePath = player.getPlayerImgPath().substring(7);
            if (!imagePath.isEmpty()) {
                File imgFile = new File(imagePath);
                if (imgFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imgViewDP.setImageBitmap(bitmap);
                }
            }
        } catch (Exception e) {

        }
        floatBtnUpdateDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdatePlayerActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        btnUpdatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPlayerName = txtPlayerName.getText().toString();
                try {
                    dataBaseHandler.updatePlayerNameImageById(playerId, newPlayerName, imgViewDP.getTag().toString());
                } catch (Exception e) {
                    dataBaseHandler.updatePlayerNameById(playerId, newPlayerName);
                }
                Toast.makeText(UpdatePlayerActivity.this, "Player details updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Setting up Image URI */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imgViewDP.setImageURI(uri);
        imgViewDP.setTag(uri.toString());
    }

    /* Navigate Back on cancel button */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(0, R.anim.slide_down);
        return super.onSupportNavigateUp();
    }
}