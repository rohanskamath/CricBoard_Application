package com.example.cricboard_application;

public class HostScorecardItem {

    String playerName,runs,balls,fours,sixes,strikeRate;

    public HostScorecardItem(String playerName, String runs, String balls, String fours, String sixes, String strikeRate) {
        this.playerName = playerName;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public void setSixes(String sixes) {
        this.sixes = sixes;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRuns() {
        return runs;
    }

    public String getBalls() {
        return balls;
    }

    public String getFours() {
        return fours;
    }

    public String getSixes() {
        return sixes;
    }

    public String getStrikeRate() {
        return strikeRate;
    }
}
