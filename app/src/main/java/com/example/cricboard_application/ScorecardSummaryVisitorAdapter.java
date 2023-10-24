package com.example.cricboard_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScorecardSummaryVisitorAdapter extends RecyclerView.Adapter<ScorecardSummaryVisitorAdapter.ViewHolder> {
    private List<VisitorScorecardItem> scorecardVisitorItems = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVisitorPlayerName, tvVisitorRuns, tvVisitorBalls, tvVisitorFours, tvVisitorSixes, tvVisitorSR,total;

        public ViewHolder(View itemView) {
            super(itemView);
            tvVisitorPlayerName = itemView.findViewById(R.id.tvVisitorPlayerName);
            tvVisitorRuns = itemView.findViewById(R.id.tvVisitorRuns);
            tvVisitorBalls=itemView.findViewById(R.id.tvVisitorBalls);
            tvVisitorFours = itemView.findViewById(R.id.tvVisitorFours);
            tvVisitorSixes = itemView.findViewById(R.id.tvVisitorSixes);
            tvVisitorSR = itemView.findViewById(R.id.tvVisitorSR);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitor_scoresummary_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VisitorScorecardItem item = scorecardVisitorItems.get(position);
        holder.tvVisitorPlayerName.setText(item.getPlayerName());
        holder.tvVisitorRuns.setText(item.getRuns());
        holder.tvVisitorBalls.setText(item.getBalls());
        holder.tvVisitorFours.setText(item.getFours());
        holder.tvVisitorSixes.setText(item.getSixes());
        holder.tvVisitorSR.setText(item.getStrikeRate());
    }

    @Override
    public int getItemCount() {
        return scorecardVisitorItems.size();
    }

    public void addData(VisitorScorecardItem item) {
        scorecardVisitorItems.add(item);
        notifyDataSetChanged();
    }
}
