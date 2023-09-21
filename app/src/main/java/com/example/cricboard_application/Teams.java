package com.example.cricboard_application;

public class Teams {

    String teamName;
    int matches,won,lost;

    public Teams(String teamName, int matches, int won, int lost) {
        this.teamName = teamName;
        this.matches = matches;
        this.won = won;
        this.lost = lost;
    }
}
