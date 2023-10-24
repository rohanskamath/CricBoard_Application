package com.example.cricboard_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ScorecardSummaryHostAdapter extends RecyclerView.Adapter<ScorecardSummaryHostAdapter.ViewHolder> {
    private ArrayList<HostScorecardItem> scorecardHostItems = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHostPlayerName, tvHostRuns, tvHostBalls, tvHostFours, tvHostSixes, tvHostSR,total;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHostPlayerName = itemView.findViewById(R.id.tvHostPlayerName);
            tvHostRuns = itemView.findViewById(R.id.tvHostRuns);
            tvHostBalls=itemView.findViewById(R.id.tvHostBalls);
            tvHostFours = itemView.findViewById(R.id.tvHostFours);
            tvHostSixes = itemView.findViewById(R.id.tvHostSixes);
            tvHostSR = itemView.findViewById(R.id.tvHostSR);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.host_scoresummary_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HostScorecardItem item = scorecardHostItems.get(position);
        holder.tvHostPlayerName.setText(item.getPlayerName());
        holder.tvHostRuns.setText(item.getRuns());
        holder.tvHostBalls.setText(item.getBalls());
        holder.tvHostFours.setText(item.getFours());
        holder.tvHostSixes.setText(item.getSixes());
        holder.tvHostSR.setText(item.getStrikeRate());

    }

    @Override
    public int getItemCount() {
        return scorecardHostItems.size();
    }

    public void addData(HostScorecardItem item) {
        scorecardHostItems.add(item);
        notifyDataSetChanged();
    }
}
