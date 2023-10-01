package com.example.cricboard_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{

    final FragmentManager fragmentManager;
    Context teamContext;
    ArrayList<Teams> teamsArrayList;
    public TeamAdapter(Context teamContext, ArrayList<Teams> teamsArrayList, FragmentManager fragmentManager) {
        this.teamContext=teamContext;
        this.teamsArrayList=teamsArrayList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View teamView= LayoutInflater.from(teamContext).inflate(R.layout.teams_layout,parent,false);
        return new TeamViewHolder(teamView,fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {

        Teams teamDetails=teamsArrayList.get(position);
        holder.tvTeamName.setText(teamDetails.teamName);
        holder.tvMatches.setText(String.valueOf(teamDetails.matches));
        holder.tvWins.setText(String.valueOf(teamDetails.won));
        holder.tvLost.setText(String.valueOf(teamDetails.lost));
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView shapeableImageView;
        TextView tvTeamName,tvMatches,tvWins,tvLost;
        ImageView imgDelete,imgEdit;
        FragmentManager fragmentManager;
        public TeamViewHolder(@NonNull View itemView,FragmentManager fragmentManager) {
            super(itemView);

            this.fragmentManager=fragmentManager;
            shapeableImageView=itemView.findViewById(R.id.title_image);
            tvTeamName=itemView.findViewById(R.id.tvTeamName);
            tvMatches=itemView.findViewById(R.id.tvMatchesValues);
            tvWins=itemView.findViewById(R.id.tvWonValues);
            tvLost=itemView.findViewById(R.id.tvLostValues);
            imgEdit=itemView.findViewById(R.id.imgEdit);
            imgDelete=itemView.findViewById(R.id.imgDelete);

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTeamDialog updateTeamDialog=new UpdateTeamDialog();
                    updateTeamDialog.show(fragmentManager, "Update Team Dailog Box");
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DeleteDialog deleteDialog=new DeleteDialog();
                    deleteDialog.show(fragmentManager,"Delete Team Dailog Box");
                }
            });
        }
    }
}
