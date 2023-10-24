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

public class PlayerDeleteDialog extends AppCompatDialogFragment {

    /* Store the PlayerId to delete */
    private int playerId;

    /* DatabaseHandler , PlayerAdapter, ArrayList Objects */
    DataBaseHandler dataBaseHandler;
    PlayerAdapter playerAdapter;
    ArrayList<PlayerNames> playerArrayList;

    public PlayerDeleteDialog(int playerId, DataBaseHandler dataBaseHandler, ArrayList<PlayerNames> playerArrayList, PlayerAdapter playerAdapter) {
        this.playerId = playerId;
        this.playerAdapter = playerAdapter;
        this.playerArrayList = playerArrayList;
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
                        dataBaseHandler.deletePlayer(playerId);
                        if (playerAdapter != null && playerArrayList != null) {
                            int position = findPlayerPositionById(playerId);
                            if (position != -1) {
                                playerArrayList.remove(position);
                                Toast.makeText(getContext(), "Player name removed successfully!!!", Toast.LENGTH_SHORT).show();
                                playerAdapter.notifyItemRemoved(position);
                            } else {
                                Toast.makeText(getContext(), "Player Name not removed!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        return deleteTeamBuilder.create();
    }

    private int findPlayerPositionById(int playerId) {
        for (int i = 0; i < playerArrayList.size(); i++) {
            if (playerArrayList.get(i).getPlayerId() == playerId) {
                return i;
            }
        }
        return -1;
    }

}
