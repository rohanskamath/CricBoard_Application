package com.example.cricboard_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class UpdateTeamDialog extends AppCompatDialogFragment {

    /* UI Objects of Custom Dialog box & Database,Adapter,arraylist Objects*/
    EditText txtNewName;
    private TeamAdapter teamAdapter;
    private ArrayList<Teams> teamsArrayList;
    DataBaseHandler dataBaseHandler;
    private Teams teamToUpdate;

    public UpdateTeamDialog(TeamAdapter teamAdapter, ArrayList<Teams> teamsArrayList, DataBaseHandler dataBaseHandler, Teams teamToUpdate) {
        this.teamAdapter = teamAdapter;
        this.teamsArrayList = teamsArrayList;
        this.dataBaseHandler = dataBaseHandler;
        this.teamToUpdate = teamToUpdate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder updateTeamBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater UpdateTeamInflater = getActivity().getLayoutInflater();
        View teamViewDailog = UpdateTeamInflater.inflate(R.layout.update_team_layout, null);

        txtNewName = teamViewDailog.findViewById(R.id.txtNewTeamName);

        // Pre-fill the EditText with the existing team name
        txtNewName.setText(teamToUpdate.getTeamName());

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
                        String newTeamName = txtNewName.getText().toString().trim();
                        if (!newTeamName.isEmpty()) {
                            teamToUpdate.setTeamName(newTeamName);
                            dataBaseHandler.updateTeam(teamToUpdate);
                            Toast.makeText(getContext(), "Team name updated successfully!!!", Toast.LENGTH_SHORT).show();
                            /* Notify the adapter that the data has changed */
                            teamAdapter.notifyDataSetChanged();
                        }
                    }
                });

        return updateTeamBuilder.create();
    }
}
