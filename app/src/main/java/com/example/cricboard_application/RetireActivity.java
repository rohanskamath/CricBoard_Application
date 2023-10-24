package com.example.cricboard_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RetireActivity extends AppCompatActivity {

    /* Not Implemented fully */

    /* UI Objects */
    RadioButton rdStriker;
    RadioButton rdNonStriker;
    EditText txtNewPlayer;
    Button replaceBatsman;

    /* Shared Preference Object */
    CricBoardSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retire);
        getSupportActionBar().setTitle(getIntent().getStringExtra("Host Team Name") + " v/s " + getIntent().getStringExtra("Visitor Team Name"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#072B5A")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = new CricBoardSharedPreferences(this);

        /* Setting UI with Java */
        rdStriker = findViewById(R.id.rdPlayer1);
        rdNonStriker = findViewById(R.id.rdPlayer2);
        txtNewPlayer = findViewById(R.id.txtNewPlayer);
        replaceBatsman = findViewById(R.id.btnReplace);

        rdStriker.setText(getIntent().getStringExtra("Striker Name"));
        rdNonStriker.setText(getIntent().getStringExtra("Non-Striker Name"));
        replaceBatsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(txtNewPlayer.getText())) {
                    if (rdStriker.isChecked()) {
                        sharedPreferences.setNewStrikerName(txtNewPlayer.getText().toString());
                        finish();
                    } else if (rdNonStriker.isChecked()) {
                        sharedPreferences.setNewStrikerName(txtNewPlayer.getText().toString());
                        finish();
                    } else {
                        Toast.makeText(RetireActivity.this, "Please check retired player!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RetireActivity.this, "Enter new Player to swap!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}