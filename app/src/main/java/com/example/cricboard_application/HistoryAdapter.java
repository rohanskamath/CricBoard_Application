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

    /* Fragment, ArrayList, Context Objects */
    final FragmentManager fragmentManager;
    Context historyContext;
    ArrayList<History> historyList;

    public HistoryAdapter(Context historyContext,ArrayList<History> historyList,FragmentManager fragmentManager) {
        this.historyContext=historyContext;
        this.historyList=historyList;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historyView= LayoutInflater.from(historyContext).inflate(R.layout.history_layout,parent,false);
        return new HistoryAdapter.HistoryViewHolder(historyView,fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {

        holder.tvDate.setText(historyList.get(position).getDate());
        holder.tvTime.setText(historyList.get(position).getTime());

        holder.tvHostTeamName.setText(historyList.get(position).getHostTeamName());
        holder.tvHostTeamRunsWickets.setText(String.valueOf(historyList.get(position).getHostTotalScore())+"/"+String.valueOf(historyList.get(position).getHostWickets())+" ("+String.valueOf(historyList.get(position).getHostOvers())+")");

        int randomHostColor = generateRandomColor();
        GradientDrawable hostDrawable = new GradientDrawable();
        hostDrawable.setShape(GradientDrawable.OVAL);
        hostDrawable.setColor(randomHostColor);
        String tvHostTeamName=historyList.get(position).getHostTeamName().toUpperCase();
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

        /* Away Details */
        holder.tvAwayTeamName.setText(historyList.get(position).getVisitorTeamName());
        holder.tvAwayTeamRunsWickets.setText(String.valueOf(historyList.get(position).getVisitorTotalScore())+"/"+String.valueOf(historyList.get(position).getVisitorWickets())+" ("+String.valueOf(historyList.get(position).getVisitorOvers()+")"));

        int randomAwayColor = generateRandomColor();
        GradientDrawable awayDrawable = new GradientDrawable();
        awayDrawable.setShape(GradientDrawable.OVAL);
        awayDrawable.setColor(randomAwayColor);
        String tvAwayTeamName=historyList.get(position).getVisitorTeamName().toUpperCase();
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

        holder.tvOverallToss.setText("Team won: "+historyList.get(position).getTeamWinningName());
    }

    private int generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        FragmentManager fragmentManager;
        /* UI Objects */
        TextView tvDate,tvTime,tvHostTeamName,tvAwayTeamName,tvHostTeamRunsWickets,tvAwayTeamRunsWickets,tvAwayTeamLogo,tvOverallToss,tvHostTeamLogo;
        public HistoryViewHolder(@NonNull View itemView,FragmentManager fragmentManager) {
            super(itemView);
            this.fragmentManager=fragmentManager;

            /* Setting UI Objects with Java */
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvHostTeamLogo = itemView.findViewById(R.id.tvHostTeamLogo);
            tvHostTeamName = itemView.findViewById(R.id.tvHostTeamName);
            tvHostTeamRunsWickets = itemView.findViewById(R.id.tvHostTeamRunsWickets);
            tvAwayTeamLogo = itemView.findViewById(R.id.tvAwayTeamLogo);
            tvAwayTeamName = itemView.findViewById(R.id.tvAwayTeamName);
            tvAwayTeamRunsWickets = itemView.findViewById(R.id.tvAwayTeamRunsWickets);
            tvOverallToss = itemView.findViewById(R.id.tvOverallToss);
        }
    }
}


