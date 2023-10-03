package com.example.cricboard_application;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
    private final TeamsInterfaceRV teamsInterfaceRV;

    public TeamAdapter(Context teamContext, ArrayList<Teams> teamsArrayList, FragmentManager fragmentManager,TeamsInterfaceRV teamsInterfaceRV) {
        this.teamContext=teamContext;
        this.teamsArrayList=teamsArrayList;
        this.fragmentManager = fragmentManager;
        this.teamsInterfaceRV=teamsInterfaceRV;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View teamView= LayoutInflater.from(teamContext).inflate(R.layout.teams_layout,parent,false);
        return new TeamViewHolder(teamView,fragmentManager,teamsInterfaceRV);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {

        Teams teamDetails=teamsArrayList.get(position);
        holder.tvTeamName.setText(teamDetails.teamName);
        holder.tvMatches.setText(String.valueOf(teamDetails.matches));
        holder.tvWins.setText(String.valueOf(teamDetails.won));
        holder.tvLost.setText(String.valueOf(teamDetails.lost));

        int randomColor = generateRandomColor();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(randomColor);
        String teamName=teamDetails.teamName.toUpperCase();
        String initial="";
        String[] words = teamName.split(" ");

        if (words.length == 1) {
            if (teamName.length() > 2) {
                char firstLetter = teamName.charAt(0);
                char secondLetter = teamName.charAt(1);
                char thirdLetter = teamName.charAt(2);
                initial = String.valueOf(firstLetter) + String.valueOf(secondLetter)+String.valueOf(thirdLetter);
            } else if (teamName.length() == 1) {
                char firstLetter = teamName.charAt(0);
                initial = String.valueOf(firstLetter);
            }
        } else if (words.length==2) {
            char firstLetter = words[0].charAt(0);
            char secondLetter = words[1].charAt(0);
            initial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        holder.txtImageView.setText(initial);
        holder.txtImageView.setBackground(drawable);
    }

    private int generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        TextView tvTeamName,tvMatches,tvWins,tvLost,txtImageView;
        ImageView imgDelete,imgEdit;
        FragmentManager fragmentManager;
        public TeamViewHolder(@NonNull View itemView,FragmentManager fragmentManager,TeamsInterfaceRV teamsInterfaceRV) {
            super(itemView);

            this.fragmentManager=fragmentManager;
            txtImageView=itemView.findViewById(R.id.title_image);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(teamsInterfaceRV != null){
                        int position=getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            teamsInterfaceRV.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
