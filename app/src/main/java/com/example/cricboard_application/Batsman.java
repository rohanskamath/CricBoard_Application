package com.example.cricboard_application;

public class Batsman {
    private int runs;
    private int balls;
    private int noFours;
    private double strikeRate;
    private int noSix;
    private boolean isOnStrike;
    private String name;

    public Batsman() {
        this.name = "";
        this.runs = 0;
        this.balls = 0;
        this.noFours = 0;
        this.strikeRate = 0;
        this.noSix = 0;
        this.isOnStrike = false;
    }

    public Batsman(String name, boolean isOnStrike) {
        this.name = name;
        this.runs = 0;
        this.balls = 0;
        this.noFours = 0;
        this.strikeRate = 0;
        this.noSix = 0;
        this.isOnStrike = isOnStrike;
    }

    public Batsman(String name, int runs, int balls, int noFours, double strikeRate, int noSix, boolean isOnStrike) {
        this.runs = runs;
        this.balls = balls;
        this.noFours = noFours;
        this.strikeRate = strikeRate;
        this.noSix = noSix;
        this.isOnStrike = isOnStrike;
        this.name = name;
    }

    public boolean isOnStrike() {
        return isOnStrike;
    }

    public void setOnStrike(boolean onStrike) {
        isOnStrike = onStrike;
    }

    public String getName() {
        if (isOnStrike)
            return name + "*";
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getRuns() {
        return runs;
    }

    public int getBalls() {
        return balls;
    }

    public int getNoFours() {
        return noFours;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public int getNoSix() {
        return noSix;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public void setNoFours(int noFours) {
        this.noFours = noFours;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public void calculateStrikeRate() {
        if (balls > 0) {
            this.strikeRate = ((double) runs / balls) * 100.0;
        } else {
            this.strikeRate = 0.0;
        }
    }

    public void setNoSix(int noSix) {
        this.noSix = noSix;
    }

    public Batsman getObject() {
        return new Batsman(name, runs, balls, noFours, strikeRate, noSix, isOnStrike);
    }

}

