package com.example.cricboard_application;

public class TeamStats {
    private int noOfMatches;
    private int noOfWins;
    private int noOfLosses;

    public TeamStats(int noOfMatches, int noOfWins, int noOfLosses) {
        this.noOfMatches = noOfMatches;
        this.noOfWins = noOfWins;
        this.noOfLosses = noOfLosses;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getNoOfWins() {
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins) {
        this.noOfWins = noOfWins;
    }

    public int getNoOfLosses() {
        return noOfLosses;
    }

    public void setNoOfLosses(int noOfLosses) {
        this.noOfLosses = noOfLosses;
    }
}
