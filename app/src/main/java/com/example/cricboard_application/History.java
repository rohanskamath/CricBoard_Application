package com.example.cricboard_application;

import android.content.ContentValues;

import java.util.ArrayList;

public class History {
    private String date;
    private String time;
    private String hostTeamName;
    private int hostTotalScore;
    private float hostOvers;
    private float visitorOvers;
    private int hostWickets;
    private String visitorTeamName;
    private int visitorTotalScore;
    private int visitorWickets;
    private String teamWinningName;

    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_HOST_TEAM_NAME = "hostTeamName";
    public static final String KEY_HOST_TOTAL_SCORE = "hostTotalScore";
    public static final String KEY_HOST_OVERS = "hostOvers";
    public static final String KEY_HOST_WICKETS = "hostWickets";
    public static final String KEY_VISITOR_TEAM_NAME = "visitorTeamName";
    public static final String KEY_VISITOR_TOTAL_SCORE = "visitorTotalScore";
    public static final String KEY_VISITOR_WICKETS = "visitorWickets";
    public static final String KEY_TEAM_WINNING_NAME = "teamWinningName";

    public History() {

    }

    public History(String date, String time, String hostTeamName, float hostOvers, int hostTotalRuns, int hostWickets,
                   String visitorTeamName, float visitorOvers, int visitorTotalScore, int visitorWickets, String teamWinningName) {
        this.date = date;
        this.time = time;
        this.hostTeamName = hostTeamName;
        this.hostOvers = hostOvers;
        this.hostTotalScore = hostTotalRuns;
        this.hostWickets = hostWickets;
        this.visitorTeamName = visitorTeamName;
        this.visitorOvers = visitorOvers;
        this.visitorTotalScore = visitorTotalScore;
        this.visitorWickets = visitorWickets;
        this.teamWinningName = teamWinningName;
    }

    public float getHostOvers() {
        return hostOvers;
    }

    public void setHostOvers(float hostOvers) {
        this.hostOvers = hostOvers;
    }

    public float getVisitorOvers() {
        return visitorOvers;
    }

    public void setVisitorOvers(float visitorOvers) {
        this.visitorOvers = visitorOvers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHostTeamName() {
        return hostTeamName;
    }

    public void setHostTeamName(String hostTeamName) {
        this.hostTeamName = hostTeamName;
    }

    public int getHostTotalScore() {
        return hostTotalScore;
    }

    public void setHostTotalScore(int hostTotalScore) {
        this.hostTotalScore = hostTotalScore;
    }

    public int getHostWickets() {
        return hostWickets;
    }

    public void setHostWickets(int hostWickets) {
        this.hostWickets = hostWickets;
    }

    public String getVisitorTeamName() {
        return visitorTeamName;
    }

    public void setVisitorTeamName(String visitorTeamName) {
        this.visitorTeamName = visitorTeamName;
    }

    public int getVisitorTotalScore() {
        return visitorTotalScore;
    }

    public void setVisitorTotalScore(int visitorTotalScore) {
        this.visitorTotalScore = visitorTotalScore;
    }

    public int getVisitorWickets() {
        return visitorWickets;
    }

    public void setVisitorWickets(int visitorWickets) {
        this.visitorWickets = visitorWickets;
    }

    public String getTeamWinningName() {
        return teamWinningName;
    }

    public void setTeamWinningName(String teamWinningName) {
        this.teamWinningName = teamWinningName;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, this.date);
        values.put(KEY_TIME, this.time);
        values.put(KEY_HOST_TEAM_NAME, this.hostTeamName);
        values.put(KEY_HOST_TOTAL_SCORE, this.hostTotalScore);
        values.put(KEY_HOST_OVERS, this.hostOvers);
        values.put(KEY_HOST_WICKETS, this.hostWickets);
        values.put(KEY_VISITOR_TEAM_NAME, this.visitorTeamName);
        values.put(KEY_VISITOR_TOTAL_SCORE, this.visitorTotalScore);
        values.put(KEY_VISITOR_WICKETS, this.visitorWickets);
        values.put(KEY_TEAM_WINNING_NAME, this.teamWinningName);
        return values;
    }

    public TeamStats getTeamStats(ArrayList<History> historyList, String teamName) {
        int noOfMatches = 0;
        int noOfWins = 0;
        int noOfLosses = 0;

        for (History history : historyList) {
            if (history.getHostTeamName().equals(teamName) || history.getVisitorTeamName().equals(teamName)) {
                noOfMatches++;
                if (history.getTeamWinningName().equals(teamName)) {
                    noOfWins++;
                } else {
                    noOfLosses++;
                }
            }
        }

        return new TeamStats(noOfMatches, noOfWins, noOfLosses);
    }
}

