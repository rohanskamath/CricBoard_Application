package com.example.cricboard_application;

import android.content.ContentValues;
import android.database.Cursor;

public class Teams {

    String teamName;
    int matches, won, lost, team_id;

    public Teams(String teamName) {
        this.teamName = teamName;
        this.matches = 0;
        this.won = 0;
        this.lost = 0;
    }

    public Teams(String teamName, int matches, int won, int lost) {
        this.teamName = teamName;
        this.matches = matches;
        this.won = won;
        this.lost = lost;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getMatches() {
        return matches;
    }

    public int getWon() {
        return won;
    }

    public int getLost() {
        return lost;
    }

    public int getTeam_id() {
        return team_id;
    }

    public static Teams fromCursor(Cursor cursor) {
        int teamIdIndex = cursor.getColumnIndex("team_id");
        int teamNameIndex = cursor.getColumnIndex("team_name");
        int matchesIndex = cursor.getColumnIndex("matches");
        int wonIndex = cursor.getColumnIndex("won");
        int lostIndex = cursor.getColumnIndex("lost");
        int teamId = cursor.getInt(teamIdIndex);

        String teamName = cursor.getString(teamNameIndex);
        int matches = cursor.getInt(matchesIndex);
        int won = cursor.getInt(wonIndex);
        int lost = cursor.getInt(lostIndex);
        return new Teams(teamName, matches, won, lost);
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("team_name", teamName);
        values.put("matches", matches);
        values.put("won", won);
        values.put("lost", lost);
        return values;
    }
}
