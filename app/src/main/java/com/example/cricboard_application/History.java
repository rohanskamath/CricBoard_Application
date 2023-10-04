package com.example.cricboard_application;

public class History {

    String date,time,teamName;
    int totalRuns,wickets;
    float overs;

    public History(String date,String time,String teamName, int totalRuns, int wickets, float overs) {
        this.date=date;
        this.time=time;
        this.teamName = teamName;
        this.totalRuns = totalRuns;
        this.wickets = wickets;
        this.overs = overs;
    }
}
