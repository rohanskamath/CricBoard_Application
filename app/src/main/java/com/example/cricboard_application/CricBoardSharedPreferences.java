package com.example.cricboard_application;

import android.content.Context;
import android.content.SharedPreferences;

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
}



