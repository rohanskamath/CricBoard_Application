package com.example.cricboard_application;

public class Bowler {
    private String name;
    private int wickets;
    private int runs;
    private double overs;
    private double economyRate;
    private int balls;
    private int maidens;

    public Bowler() {
        this.name = "";
        this.wickets = 0;
        this.runs = 0;
        this.overs = 0.0;
        this.balls = 0;
        this.maidens = 0;
    }

    public Bowler(String name) {
        this.name = name;
        this.wickets = 0;
        this.runs = 0;
        this.overs = 0.0;
        this.balls = 0;
        this.maidens = 0;
    }

    public Bowler(String name, int wickets, int runs, double overs, int balls, int maidens) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEconomyRate(double economyRate) {
        this.economyRate = economyRate;
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

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
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

    public void calculateEconomyRate() {
        if (overs > 0) {
            this.economyRate = runs / overs;
        } else {
            this.economyRate = 0.0;
        }
    }

}

