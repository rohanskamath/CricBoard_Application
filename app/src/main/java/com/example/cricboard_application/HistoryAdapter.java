package com.example.cricboard_application;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    final FragmentManager fragmentManager;
    Context historyContext;
    ArrayList<History> historyHostArrayList;
    ArrayList<History> historyAwayArrayList;
    HistoryInterfaceRV historyInterfaceRV;

    public HistoryAdapter(Context historyContext, ArrayList<History> historyHostArrayList, ArrayList<History> historyAwayArrayList,FragmentManager fragmentManager,HistoryInterfaceRV historyInterfaceRV) {
        this.historyContext=historyContext;
        this.historyHostArrayList=historyHostArrayList;
        this.historyAwayArrayList=historyAwayArrayList;
        this.fragmentManager=fragmentManager;
        this.historyInterfaceRV= historyInterfaceRV;

    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historyView= LayoutInflater.from(historyContext).inflate(R.layout.history_layout,parent,false);
        return new HistoryAdapter.HistoryViewHolder(historyView,fragmentManager,historyInterfaceRV);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        History HistoryHostDetails=historyHostArrayList.get(position);
        History HistoryAwayDetails=historyAwayArrayList.get(position);

        holder.tvDate.setText(String.valueOf(HistoryHostDetails.date));
        holder.tvTime.setText(String.valueOf(HistoryHostDetails.time));

        //Host Details
        holder.tvHostTeamName.setText(HistoryHostDetails.teamName);
        holder.tvHostTeamRunsWickets.setText(String.valueOf(HistoryHostDetails.totalRuns)+"/"+String.valueOf(HistoryHostDetails.wickets)+" ("+String.valueOf(HistoryHostDetails.overs)+")");

        int randomHostColor = generateRandomColor();
        GradientDrawable hostDrawable = new GradientDrawable();
        hostDrawable.setShape(GradientDrawable.OVAL);
        hostDrawable.setColor(randomHostColor);
        String tvHostTeamName=HistoryHostDetails.teamName.toUpperCase();
        String Hostinitial="";
        String[] hostWords = tvHostTeamName.split(" ");

        if (hostWords.length == 1) {
            if (tvHostTeamName.length() > 2) {
                char firstLetter = tvHostTeamName.charAt(0);
                char secondLetter = tvHostTeamName.charAt(1);
                char thirdLetter = tvHostTeamName.charAt(2);
                Hostinitial = String.valueOf(firstLetter) + String.valueOf(secondLetter)+String.valueOf(thirdLetter);
            } else if (tvHostTeamName.length() == 1) {
                char firstLetter = tvHostTeamName.charAt(0);
                Hostinitial = String.valueOf(firstLetter);
            }
        } else if (hostWords.length==2) {
            char firstLetter = hostWords[0].charAt(0);
            char secondLetter = hostWords[1].charAt(0);
            Hostinitial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        holder.tvHostTeamLogo.setText(Hostinitial);
        holder.tvHostTeamLogo.setBackground(hostDrawable);

        //Away Details
        holder.tvAwayTeamName.setText(HistoryAwayDetails.teamName);
        holder.tvAwayTeamRunsWickets.setText(String.valueOf(HistoryAwayDetails.totalRuns)+"/"+String.valueOf(HistoryAwayDetails.wickets)+" ("+String.valueOf(HistoryAwayDetails.overs)+")");

        int randomAwayColor = generateRandomColor();
        GradientDrawable awayDrawable = new GradientDrawable();
        awayDrawable.setShape(GradientDrawable.OVAL);
        awayDrawable.setColor(randomAwayColor);
        String tvAwayTeamName=HistoryAwayDetails.teamName.toUpperCase();
        String Awayinitial="";
        String[] awayWords = tvAwayTeamName.split(" ");

        if (awayWords.length == 1) {
            if (tvAwayTeamName.length() > 2) {
                char firstLetter = tvAwayTeamName.charAt(0);
                char secondLetter = tvAwayTeamName.charAt(1);
                char thirdLetter = tvAwayTeamName.charAt(2);
                Awayinitial = String.valueOf(firstLetter) + String.valueOf(secondLetter)+String.valueOf(thirdLetter);
            } else if (tvAwayTeamName.length() == 1) {
                char firstLetter = tvAwayTeamName.charAt(0);
                Awayinitial = String.valueOf(firstLetter);
            }
        } else if (awayWords.length==2) {
            char firstLetter = awayWords[0].charAt(0);
            char secondLetter = awayWords[1].charAt(0);
            Awayinitial = String.valueOf(firstLetter) + String.valueOf(secondLetter);
        }
        holder.tvAwayTeamLogo.setText(Awayinitial);
        holder.tvAwayTeamLogo.setBackground(awayDrawable);
        holder.tvOverallToss.setText("Team 1 won the toss and opted to Bowl First");
    }

    private int generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }


    @Override
    public int getItemCount() {
        return historyHostArrayList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        FragmentManager fragmentManager;
        TextView tvDate,tvTime,tvHostTeamName,tvAwayTeamName,tvHostTeamRunsWickets,tvAwayTeamRunsWickets,tvAwayTeamLogo,tvOverallToss,tvHostTeamLogo;
        public HistoryViewHolder(@NonNull View itemView,FragmentManager fragmentManager, HistoryInterfaceRV historyInterfaceRV) {
            super(itemView);
            this.fragmentManager=fragmentManager;
            tvDate=itemView.findViewById(R.id.tvDate);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvHostTeamName=itemView.findViewById(R.id.tvHostTeamName);
            tvAwayTeamName=itemView.findViewById(R.id.tvAwayTeamName);
            tvHostTeamRunsWickets=itemView.findViewById(R.id.tvHostTeamRunsWickets);
            tvAwayTeamRunsWickets=itemView.findViewById(R.id.tvAwayTeamRunsWickets);
            tvAwayTeamLogo=itemView.findViewById(R.id.tvAwayTeamLogo);
            tvHostTeamLogo=itemView.findViewById(R.id.tvHostTeamLogo);
            tvOverallToss=itemView.findViewById(R.id.tvOverallToss);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(historyInterfaceRV != null){
                        int position=getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            historyInterfaceRV.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}


