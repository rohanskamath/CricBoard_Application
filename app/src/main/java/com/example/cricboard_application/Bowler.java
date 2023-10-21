package com.example.cricboard_application;

public class Bowler {
    private int wickets;
    private int runs;
    private int overs;
    private double economyRate;
    private int balls;
    private int maidens;

    public Bowler(){
        this.wickets = 0;
        this.runs = 0;
        this.overs = 0;
        this.balls = 0;
        this.maidens = 0;
    }
    public Bowler(int wickets, int runs, int overs, int balls, int maidens) {
        this.wickets = wickets;
        this.runs = runs;
        this.overs = overs;
        this.balls = balls;
        this.maidens = maidens;

        if (overs > 0) {
            this.economyRate = runs / (double) overs;
        } else {
            this.economyRate = 0.0; // Prevent division by zero
        }
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public double getEconomyRate() {
        return economyRate;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getMaidens() {
        return maidens;
    }

    public void setMaidens(int maidens) {
        this.maidens = maidens;
    }
}

