package com.example.cricboard_application;

import android.content.ContentValues;

public class PlayerNames {

    private static final String TABLE_NAME_PLAYER = "player";
    private static final String PLAYER_ID = "player_id";
    private static final String TEAM_ID_PLAYER = "team_id";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_IMG_PATH = "player_img";
    private static final String PLAYER_MATCHES = "matches";
    private static final String PLAYER_RUNS = "Runs";
    private static final String PLAYER_NOTOUTS = "not_outs";
    private static final String PLAYER_BEST_SCORE = "best_score";
    private static final String PLAYER_SR = "SR";
    private static final String PLAYER_AVERAGE = "average";
    private static final String PLAYER_FOURS = "fours";
    private static final String PLAYER_SIXES = "sixes";
    private static final String PLAYER_THIRTIES = "thirties";
    private static final String PLAYER_FIFTIES = "fifties";
    private static final String PLAYER_HUNDREDS = "hundreds";
    private static final String PLAYER_OVERS = "overs";
    private static final String PLAYER_WICKETS = "wickets";
    private static final String PLAYER_RUNS_BOWL = "BOWL_RUNS";
    private static final String PLAYER_FWICKETS = "four_wickets";
    private static final String PLAYER_FIFWICKETS = "five_wickets";

    private int playerId;
    private int teamId;
    private String playerName;
    private String playerImgPath;
    private int playerMatches;
    private int playerRuns;
    private int playerNotOuts;
    private int playerBestScore;
    private double playerSR;
    private double playerAverage;
    private int playerFours;
    private int playerSixes;
    private int playerThirties;
    private int playerFifties;
    private int playerHundreds;
    private double playerOvers;
    private int playerWickets;
    private int playerRunsBowl;
    private int playerFoursWickets;
    private int playerFiveWickets;

    public PlayerNames() {

    }

    public PlayerNames(int team_id, String PName) {
        this.teamId = team_id;
        this.playerName = PName;
        this.playerImgPath = "";
        this.playerId = 0;
        this.playerMatches = 0;
        this.playerRuns = 0;
        this.playerNotOuts = 0;
        this.playerBestScore = 0;
        this.playerSR = 0.0;
        this.playerAverage = 0.0;
        this.playerFours = 0;
        this.playerSixes = 0;
        this.playerThirties = 0;
        this.playerFifties = 0;
        this.playerHundreds = 0;
        this.playerOvers = 0.0;
        this.playerWickets = 0;
        this.playerRunsBowl = 0;
        this.playerFoursWickets = 0;
        this.playerFiveWickets = 0;
    }

    public PlayerNames(int team_id, String PName, String imgPath) {
        this.teamId = team_id;
        this.playerName = PName;
        this.playerImgPath = imgPath;
        this.playerId = 0;
        this.playerMatches = 0;
        this.playerRuns = 0;
        this.playerNotOuts = 0;
        this.playerBestScore = 0;
        this.playerSR = 0.0;
        this.playerAverage = 0.0;
        this.playerFours = 0;
        this.playerSixes = 0;
        this.playerThirties = 0;
        this.playerFifties = 0;
        this.playerHundreds = 0;
        this.playerOvers = 0.0;
        this.playerWickets = 0;
        this.playerRunsBowl = 0;
        this.playerFoursWickets = 0;
        this.playerFiveWickets = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerImgPath() {
        return playerImgPath;
    }

    public void setPlayerImgPath(String playerImgPath) {
        this.playerImgPath = playerImgPath;
    }

    public int getPlayerMatches() {
        return playerMatches;
    }

    public void setPlayerMatches(int playerMatches) {
        this.playerMatches = playerMatches;
    }

    public int getPlayerRuns() {
        return playerRuns;
    }

    public void setPlayerRuns(int playerRuns) {
        this.playerRuns = playerRuns;
    }

    public int getPlayerNotOuts() {
        return playerNotOuts;
    }

    public void setPlayerNotOuts(int playerNotOuts) {
        this.playerNotOuts = playerNotOuts;
    }

    public int getPlayerBestScore() {
        return playerBestScore;
    }

    public void setPlayerBestScore(int playerBestScore) {
        this.playerBestScore = playerBestScore;
    }

    public double getPlayerSR() {
        return playerSR;
    }

    public void setPlayerSR(double playerSR) {
        this.playerSR = playerSR;
    }

    public double getPlayerAverage() {
        return playerAverage;
    }

    public void setPlayerAverage(double playerAverage) {
        this.playerAverage = playerAverage;
    }

    public int getPlayerFours() {
        return playerFours;
    }

    public void setPlayerFours(int playerFours) {
        this.playerFours = playerFours;
    }

    public int getPlayerSixes() {
        return playerSixes;
    }

    public void setPlayerSixes(int playerSixes) {
        this.playerSixes = playerSixes;
    }

    public int getPlayerThirties() {
        return playerThirties;
    }

    public void setPlayerThirties(int playerThirties) {
        this.playerThirties = playerThirties;
    }

    public int getPlayerFifties() {
        return playerFifties;
    }

    public void setPlayerFifties(int playerFifties) {
        this.playerFifties = playerFifties;
    }

    public int getPlayerHundreds() {
        return playerHundreds;
    }

    public void setPlayerHundreds(int playerHundreds) {
        this.playerHundreds = playerHundreds;
    }

    public double getPlayerOvers() {
        return playerOvers;
    }

    public void setPlayerOvers(double playerOvers) {
        this.playerOvers = playerOvers;
    }

    public int getPlayerWickets() {
        return playerWickets;
    }

    public void setPlayerWickets(int playerWickets) {
        this.playerWickets = playerWickets;
    }

    public int getPlayerRunsBowl() {
        return playerRunsBowl;
    }

    public void setPlayerRunsBowl(int playerRunsBowl) {
        this.playerRunsBowl = playerRunsBowl;
    }

    public int getPlayerFoursWickets() {
        return playerFoursWickets;
    }

    public void setPlayerFoursWickets(int playerFoursWickets) {
        this.playerFoursWickets = playerFoursWickets;
    }

    public int getPlayerFiveWickets() {
        return playerFiveWickets;
    }

    public void setPlayerFiveWickets(int playerFiveWickets) {
        this.playerFiveWickets = playerFiveWickets;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TEAM_ID_PLAYER, this.teamId);
        values.put(PLAYER_NAME, this.playerName);
        values.put(PLAYER_IMG_PATH, this.playerImgPath);
        values.put(PLAYER_MATCHES, this.playerMatches);
        values.put(PLAYER_RUNS, this.playerRuns);
        values.put(PLAYER_NOTOUTS, this.playerNotOuts);
        values.put(PLAYER_BEST_SCORE, this.playerBestScore);
        values.put(PLAYER_SR, this.playerSR);
        values.put(PLAYER_AVERAGE, this.playerAverage);
        values.put(PLAYER_FOURS, this.playerFours);
        values.put(PLAYER_SIXES, this.playerSixes);
        values.put(PLAYER_THIRTIES, this.playerThirties);
        values.put(PLAYER_FIFTIES, this.playerFifties);
        values.put(PLAYER_HUNDREDS, this.playerHundreds);
        values.put(PLAYER_OVERS, this.playerOvers);
        values.put(PLAYER_WICKETS, this.playerWickets);
        values.put(PLAYER_RUNS_BOWL, this.playerRunsBowl);
        values.put(PLAYER_FWICKETS, this.playerFoursWickets);
        values.put(PLAYER_FIFWICKETS, this.playerFiveWickets);

        return values;
    }

}

