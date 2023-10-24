package com.example.cricboard_application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    /* Fragment, ArrayList, Context , DatabaseHandler Objects */
    Context playerContext;
    ArrayList<PlayerNames> playerArrayList;
    final FragmentManager fragmentManager;
    private final PlayerInterfaceRV playerInterfaceRV;
    DataBaseHandler dataBaseHandler;

    // Inflating layout using custom adapter
    public PlayerAdapter(Context playerContext, ArrayList<PlayerNames> playerArrayList, FragmentManager fragmentManager, PlayerInterfaceRV playerInterfaceRV) {
        this.playerContext = playerContext;
        this.playerArrayList = playerArrayList;
        this.fragmentManager = fragmentManager;
        this.playerInterfaceRV = playerInterfaceRV;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(playerContext).inflate(R.layout.players_layout, parent, false);
        return new PlayerAdapter.PlayerViewHolder(playerView, fragmentManager, playerInterfaceRV);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerViewHolder holder, int position) {
        PlayerNames playerDetails = playerArrayList.get(position);
        holder.tvPlayerName.setText(playerDetails.getPlayerName());

        /* Player Logo */
        int randomColor = generateRandomColor();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(randomColor);
        String playerName = playerDetails.getPlayerName().toUpperCase();
        String initial = "";
        String[] words = playerName.split(" ");

        /* DataBaseHandler object initialization */
        dataBaseHandler = new DataBaseHandler(playerContext);

        if (words.length == 1) {
            if (playerName.length() > 2) {
                char firstLetter = playerName.charAt(0);
                char secondLetter = playerName.charAt(1);
                initial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
            } else if (playerName.length() == 1) {
                char firstLetter = playerName.charAt(0);
                initial = String.valueOf(firstLetter);
            }
        } else if (words.length == 2) {
            char firstLetter = words[0].charAt(0);
            char secondLetter = words[1].charAt(0);
            initial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        holder.tvImgPlayerName.setText(initial);
        holder.tvImgPlayerName.setBackground(drawable);

        /* get playerId from arraylist */
        int playerId = playerArrayList.get(position).getPlayerId();

        holder.imgPlayerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updatePlayerIntent = new Intent(playerContext, UpdatePlayerActivity.class);
                updatePlayerIntent.putExtra("Player Name", playerDetails.getPlayerName());
                updatePlayerIntent.putExtra("Player ID", playerDetails.getPlayerId());
                playerContext.startActivity(updatePlayerIntent);
            }
        });

        holder.imgPlayerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerDeleteDialog playerDeleteDialog = new PlayerDeleteDialog(playerId, dataBaseHandler, playerArrayList, PlayerAdapter.this);
                playerDeleteDialog.show(fragmentManager, "Delete Player Name");
            }
        });
    }

    private int generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {

        /* UI Objects */
        TextView tvPlayerName, tvImgPlayerName;
        ImageView imgPlayerDelete, imgPlayerEdit;
        FragmentManager fragmentManager;

        public PlayerViewHolder(@NonNull View itemView, FragmentManager fragmentManager, PlayerInterfaceRV playerInterfaceRV) {
            super(itemView);
            this.fragmentManager = fragmentManager;

            /* Setting UI elements with Java */
            tvImgPlayerName = itemView.findViewById(R.id.title_image);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            imgPlayerDelete = itemView.findViewById(R.id.imgPlayerDelete);
            imgPlayerEdit = itemView.findViewById(R.id.imgPlayerEdit);

            /* OnClick of Item in recyclerview */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playerInterfaceRV != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            playerInterfaceRV.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
