package com.example.cricboard_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateTeamDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder updateTeamBuilder=new AlertDialog.Builder(getContext());
        LayoutInflater UpdateTeamInflater=getActivity().getLayoutInflater();

        View teamViewDailog=UpdateTeamInflater.inflate(R.layout.update_team_layout,null);
        updateTeamBuilder.setView(teamViewDailog)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return updateTeamBuilder.create();
    }
}
