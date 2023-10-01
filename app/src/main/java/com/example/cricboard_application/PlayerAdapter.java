package com.example.cricboard_application;

import android.content.Context;
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

    Context playerContext;
    ArrayList<PlayerNames> playerArrayList;
    final FragmentManager fragmentManager;

    // Inflating layout using custom adapter
    public PlayerAdapter(Context playerContext, ArrayList<PlayerNames> playerArrayList,FragmentManager fragmentManager){
        this.playerContext=playerContext;
        this.playerArrayList=playerArrayList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView= LayoutInflater.from(playerContext).inflate(R.layout.players_layout,parent,false);
        return new PlayerAdapter.PlayerViewHolder(playerView,fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerViewHolder holder, int position) {
        PlayerNames playerDetails=playerArrayList.get(position);
        holder.tvPlayerName.setText(playerDetails.playerName);
    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlayerName;
        ImageView imgPlayerDelete;
        FragmentManager fragmentManager;
        public PlayerViewHolder(@NonNull View itemView, FragmentManager fragmentManager) {
            super(itemView);
            this.fragmentManager=fragmentManager;

            tvPlayerName=itemView.findViewById(R.id.tvPlayerName);
            imgPlayerDelete=itemView.findViewById(R.id.imgPlayerDelete);

            imgPlayerDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DeleteDialog deleteDialog=new DeleteDialog();
                    deleteDialog.show(fragmentManager,"Delete Player Name");
                }
            });
        }
    }
}
