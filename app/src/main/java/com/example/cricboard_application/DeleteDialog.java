package com.example.cricboard_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class DeleteDialog extends AppCompatDialogFragment {

    /* Store the team_id to delete */
    private int teamId;

    /* Database , ArrayList, TeamAdapter Objects */
    DataBaseHandler dataBaseHandler;
    TeamAdapter teamAdapter;
    ArrayList<Teams> teamsArrayList;

    public DeleteDialog(int teamId, DataBaseHandler dataBaseHandler, ArrayList<Teams> teamsArrayList, TeamAdapter teamAdapter) {
        this.teamId = teamId;
        this.teamAdapter = teamAdapter;
        this.teamsArrayList = teamsArrayList;
        this.dataBaseHandler = dataBaseHandler;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder deleteTeamBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater deleteTeamInflater = getActivity().getLayoutInflater();
        View teamViewDialog = deleteTeamInflater.inflate(R.layout.delete_layout, null);
        deleteTeamBuilder.setView(teamViewDialog)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataBaseHandler.deleteTeam(teamId);
                        if (teamAdapter != null && teamsArrayList != null) {
                            int position = findTeamPositionById(teamId);
                            if (position != -1) {
                                teamsArrayList.remove(position);
                                Toast.makeText(getContext(), "Team name removed successfully!!!", Toast.LENGTH_SHORT).show();
                                teamAdapter.notifyItemRemoved(position);
                            }
                        }
                    }
                });

        return deleteTeamBuilder.create();
    }

    private int findTeamPositionById(int teamId) {
        for (int i = 0; i < teamsArrayList.size(); i++) {
            if (teamsArrayList.get(i).getTeam_id() == teamId) {
                return i;
            }
        }
        return -1;
    }

}
