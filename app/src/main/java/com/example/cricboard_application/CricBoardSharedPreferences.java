package com.example.cricboard_application;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CricBoardSharedPreferences {
    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_HOST_TEAM_NAME = "HostTeamName";
    private static final String KEY_VISITOR_TEAM_NAME = "VisitorTeamName";
    private static final String KEY_TOSS_WON_BY = "TossWonBy";
    private static final String KEY_OPTED_TO = "OptedTo";
    private static final String KEY_OVERS = "Overs";

    private static final String KEY_STRIKER_NAME = "StrikerName";
    private static final String KEY_NON_STRIKER_NAME = "NonStrikerName";
    private static final String KEY_BOWLER_NAME = "BowlerName";

    private static final String KEY_NEW_STRIKER_NAME = "NewStrikerName";

    private static final String KEY_TOTAL_TEAM_RUNS = "TotalTeamRuns";
    private static final String KEY_TOTAL_TEAM_WICKETS = "TotalTeamWickets";
    private static final String KEY_TOTAL_OVERS = "TotalOvers";
    private static final String KEY_CURRENT_RUN_RATE = "CurrentRunRate";

    private static final String KEY_IS_TARGET_ACTIVITY_DONE = "IsTargetActivityDone";

    private static final String KEY_WINNING_TEAM_NAME = "WinningTeamName";

    private static final String KEY_TOTAL_FIRST_TEAM_RUNS = "firstTeamRuns";
    private static final String KEY_TOTAL_FIRST_TEAM_OVERS = "firstTeamOvers";
    private static final String KEY_TOTAL_FIRST_TEAM_WICKETS = "firstTeamWickets";

    private static final String KEY_HISTORY_LIST = "historyList";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CricBoardSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getHostTeamName() {
        return sharedPreferences.getString(KEY_HOST_TEAM_NAME, "");
    }

    public void setHostTeamName(String hostTeamName) {
        editor.putString(KEY_HOST_TEAM_NAME, hostTeamName);
        editor.apply();
    }

    public String getVisitorTeamName() {
        return sharedPreferences.getString(KEY_VISITOR_TEAM_NAME, "");
    }

    public void setVisitorTeamName(String visitorTeamName) {
        editor.putString(KEY_VISITOR_TEAM_NAME, visitorTeamName);
        editor.apply();
    }

    public String getTossWonBy() {
        return sharedPreferences.getString(KEY_TOSS_WON_BY, "");
    }

    public void setTossWonBy(String tossWonBy) {
        editor.putString(KEY_TOSS_WON_BY, tossWonBy);
        editor.apply();
    }

    public String getOptedTo() {
        return sharedPreferences.getString(KEY_OPTED_TO, "");
    }

    public void setOptedTo(String optedTo) {
        editor.putString(KEY_OPTED_TO, optedTo);
        editor.apply();
    }

    public float getOvers() {
        return sharedPreferences.getFloat(KEY_OVERS, 0.0f);
    }

    public void setOvers(float overs) {
        editor.putFloat(KEY_OVERS, overs);
        editor.apply();
    }

    public String getStrikerName() {
        return sharedPreferences.getString(KEY_STRIKER_NAME, "");
    }

    public void setStrikerName(String strikerName) {
        editor.putString(KEY_STRIKER_NAME, strikerName);
        editor.apply();
    }

    public String getNonStrikerName() {
        return sharedPreferences.getString(KEY_NON_STRIKER_NAME, "");
    }

    public void setNonStrikerName(String nonStrikerName) {
        editor.putString(KEY_NON_STRIKER_NAME, nonStrikerName);
        editor.apply();
    }

    public String getBowlerName() {
        return sharedPreferences.getString(KEY_BOWLER_NAME, "");
    }

    public void setBowlerName(String bowlerName) {
        editor.putString(KEY_BOWLER_NAME, bowlerName);
        editor.apply();
    }

    public String getNewStrikerName() {
        return sharedPreferences.getString(KEY_NEW_STRIKER_NAME, "");
    }

    public void setNewStrikerName(String newStrikerName) {
        editor.putString(KEY_NEW_STRIKER_NAME, newStrikerName);
        editor.apply();
    }

    public int getTotalTeamRuns() {
        return sharedPreferences.getInt(KEY_TOTAL_TEAM_RUNS, 0);
    }

    public void setTotalTeamRuns(int totalTeamRuns) {
        editor.putInt(KEY_TOTAL_TEAM_RUNS, totalTeamRuns);
        editor.apply();
    }

    public int getTotalTeamWickets() {
        return sharedPreferences.getInt(KEY_TOTAL_TEAM_WICKETS, 0);
    }

    public void setTotalTeamWickets(int totalTeamWickets) {
        editor.putInt(KEY_TOTAL_TEAM_WICKETS, totalTeamWickets);
        editor.apply();
    }

    public float getTotalOvers() {
        return sharedPreferences.getFloat(KEY_TOTAL_OVERS, 0.0f);
    }

    public void setTotalOvers(float totalOvers) {
        editor.putFloat(KEY_TOTAL_OVERS, totalOvers);
        editor.apply();
    }

    public float getCurrentRunRate() {
        return sharedPreferences.getFloat(KEY_CURRENT_RUN_RATE, 0.0f);
    }

    public void setCurrentRunRate() {
        editor.putFloat(KEY_CURRENT_RUN_RATE, calculateCurrentRunRate());
        editor.apply();
    }

    public float calculateCurrentRunRate() {
        int totalRuns = getTotalTeamRuns();
        float totalOvers = getTotalOvers();
        if (totalOvers > 0) {
            return totalRuns / totalOvers;
        } else {
            return (0.0f);
        }
    }
    public boolean getIsTargetActivityDone() {
        return sharedPreferences.getBoolean(KEY_IS_TARGET_ACTIVITY_DONE, false);
    }

    public void setIsTargetActivityDone(boolean isDone) {
        editor.putBoolean(KEY_IS_TARGET_ACTIVITY_DONE, isDone);
        editor.apply();
    }

    public String getWinningTeamName() {
        return sharedPreferences.getString(KEY_WINNING_TEAM_NAME, "");
    }

    public void setWinningTeamName(String winningTeamName) {
        editor.putString(KEY_WINNING_TEAM_NAME, winningTeamName);
        editor.apply();
    }

    public int getTotalFirstTeamRuns() {
        return sharedPreferences.getInt(KEY_TOTAL_FIRST_TEAM_RUNS, 0);
    }

    public void setTotalFirstTeamRuns(int totalFirstTeamRuns) {
        editor.putInt(KEY_TOTAL_FIRST_TEAM_RUNS, totalFirstTeamRuns);
        editor.apply();
    }

    public float getTotalFirstTeamOvers() {
        return sharedPreferences.getFloat(KEY_TOTAL_FIRST_TEAM_OVERS, 0.0f);
    }

    public void setTotalFirstTeamOvers(float totalFirstTeamOvers) {
        editor.putFloat(KEY_TOTAL_FIRST_TEAM_OVERS, totalFirstTeamOvers);
        editor.apply();
    }

    public int getTotalFirstTeamWickets() {
        return sharedPreferences.getInt(KEY_TOTAL_FIRST_TEAM_WICKETS, 0);
    }

    public void setTotalFirstTeamWickets(int totalFirstTeamWickets) {
        editor.putInt(KEY_TOTAL_FIRST_TEAM_WICKETS, totalFirstTeamWickets);
        editor.apply();
    }

    // Save the ArrayList of objects to SharedPreferences
    public  void saveObjectList(ArrayList<History> objectList) {

        // Convert the ArrayList to a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(objectList);

        editor.putString(KEY_HISTORY_LIST, json);
        editor.apply();
    }

    // Retrieve the ArrayList of objects from SharedPreferences
    public  ArrayList<History> getObjectList() {

        // Retrieve the JSON string from SharedPreferences
        String json = sharedPreferences.getString(KEY_HISTORY_LIST, null);

        if (json != null) {
            // Convert the JSON string back to an ArrayList
            Gson gson = new Gson();
            History[] objectArray = gson.fromJson(json, History[].class);

            if (objectArray != null) {
                ArrayList<History> objectList = new ArrayList<>(Arrays.asList(objectArray));
                return objectList;
            }
        }

        return new ArrayList<>(); // Return an empty list if data doesn't exist
    }
}