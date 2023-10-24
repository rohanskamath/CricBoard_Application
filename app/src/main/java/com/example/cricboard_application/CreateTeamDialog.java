package com.example.cricboard_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cricboard_application.R;

import java.util.ArrayList;

public class CreateTeamDialog extends AppCompatDialogFragment {

    /* UI Objects of Custom Dialog box & Database,Adapter,arraylist Objects*/
    EditText txtNewName;
    private TeamAdapter teamAdapter;
    private ArrayList<Teams> teamsArrayList;
    DataBaseHandler dataBaseHandler;

    public CreateTeamDialog(TeamAdapter teamAdapter, ArrayList<Teams> teamsArrayList, DataBaseHandler dataBaseHandler) {
        this.teamAdapter = teamAdapter;
        this.teamsArrayList = teamsArrayList;
        this.dataBaseHandler = dataBaseHandler;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder teamBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater teamInflater = getActivity().getLayoutInflater();

        /* Creating view for working of UI Objects */
        View teamViewDialog = teamInflater.inflate(R.layout.create_team_layout, null);
        txtNewName = teamViewDialog.findViewById(R.id.txtNewTeam);

        teamBuilder.setView(teamViewDialog)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!TextUtils.isEmpty(txtNewName.getText())) {
                            dataBaseHandler.addTeam(txtNewName.getText().toString());
                            if (teamAdapter != null && teamsArrayList != null) {
                                Teams newTeam = new Teams(txtNewName.getText().toString());
                                teamsArrayList.add(newTeam);
                                Toast.makeText(getContext(), "New team added successfully!!!", Toast.LENGTH_SHORT).show();
                                teamAdapter.notifyItemInserted(teamsArrayList.size() - 1);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Team Name is required!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return teamBuilder.create();
    }
}
