package com.example.cricboard_application;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CricBoard.db";
    private static final int DATABASE_VERSION = 1;

    //Teams Table instances
    private static final String TABLE_NAME_TEAM = "Teams";
    private static final String TEAM_ID = "team_id";
    private static final String TEAM_NAME = "team_name";
    private static final String TEAM_MATCHES = "matches";
    private static final String TEAM_WON = "won";
    private static final String TEAM_LOST = "lost";
    private static final String SQL_CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_NAME_TEAM + " ("
            + TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEAM_NAME + " TEXT NOT NULL, "
            + TEAM_MATCHES + " INTEGER NOT NULL, "
            + TEAM_WON + " INTEGER NOT NULL, "
            + TEAM_LOST + " INTEGER NOT NULL );";

    //Player Table instances
    private static final String TABLE_NAME_PLAYER = "Player";
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
    private static final String SQL_CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_NAME_PLAYER + " ("
            + PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEAM_ID_PLAYER + " INTEGER, "
            + PLAYER_NAME + " TEXT NOT NULL, "
            + PLAYER_IMG_PATH + " TEXT,"
            + PLAYER_MATCHES + " INTEGER NOT NULL, "
            + PLAYER_RUNS + " INTEGER, "
            + PLAYER_NOTOUTS + " INTEGER, "
            + PLAYER_BEST_SCORE + " INTEGER, "
            + PLAYER_SR + " REAL, "
            + PLAYER_AVERAGE + " REAL, "
            + PLAYER_FOURS + " INTEGER, "
            + PLAYER_SIXES + " INTEGER, "
            + PLAYER_THIRTIES + " INTEGER, "
            + PLAYER_FIFTIES + " INTEGER, "
            + PLAYER_HUNDREDS + " INTEGER, "
            + PLAYER_OVERS + " REAL, "
            + PLAYER_WICKETS + " INTEGER, "
            + PLAYER_RUNS_BOWL + " INTEGER, "
            + PLAYER_FWICKETS + " INTEGER, "
            + PLAYER_FIFWICKETS + " INTEGER, "
            + " FOREIGN KEY (" + TEAM_ID + ") REFERENCES " + TABLE_NAME_TEAM + "(" + TEAM_ID + ")"
            + ");";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* Create Team Table */
        sqLiteDatabase.execSQL(SQL_CREATE_TEAMS_TABLE);
        Log.d("TABLE CREATION: ","Teams created");
        /* Create Player Table */
        sqLiteDatabase.execSQL(SQL_CREATE_PLAYERS_TABLE);
        Log.d("TABLE CREATION: ","Players created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TEAM);
        /* Create table again*/
        onCreate(sqLiteDatabase);
    }

    /* Method to add a new team "teams" table */
    public void addTeam(String teamName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Teams newTeam = new Teams(teamName);
        // Inserting Row
        db.insert(TABLE_NAME_TEAM, null, newTeam.toContentValues());
        db.close();
    }

    /* Function to Retrieve all the teams present in the teams table */
    @SuppressLint("Range")
    public ArrayList<Teams> getAllTeams() {

        ArrayList<Teams> teamsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {TEAM_ID, TEAM_NAME, TEAM_MATCHES, TEAM_WON, TEAM_LOST};
        String whereClause = null;
        String[] selectionArgs = null;
        Cursor cursor = db.query(TABLE_NAME_TEAM, columns, whereClause, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int teamId = cursor.getInt(cursor.getColumnIndex(TEAM_ID));
                String teamName = cursor.getString(cursor.getColumnIndex(TEAM_NAME));
                int matches = cursor.getInt(cursor.getColumnIndex(TEAM_MATCHES));
                int won = cursor.getInt(cursor.getColumnIndex(TEAM_WON));
                int lost = cursor.getInt(cursor.getColumnIndex(TEAM_LOST));

                Teams team = new Teams(teamName, matches, won, lost);
                team.setTeam_id(teamId);
                teamsList.add(team);
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return teamsList;
    }

    /* Function to delete a team by team_id  in teams table */
    public void deleteTeam(int teamId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = TEAM_ID + " = ?";
        String[] whereArgs = {String.valueOf(teamId)};
        db.delete(TABLE_NAME_TEAM, whereClause, whereArgs);
        db.close();
    }

    /* Function to update a team's name in the teams table */
    public void updateTeam(Teams updatedTeam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = updatedTeam.toContentValues();
        String whereClause = TEAM_ID + " = ?";
        String[] whereArgs = {String.valueOf(updatedTeam.getTeam_id())};
        db.update(TABLE_NAME_TEAM, values, whereClause, whereArgs);
        db.close();
    }

    /* Method to add a new player "player" table */
    public void addPlayer(PlayerNames players) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = players.toContentValues();
        // Inserting Row
        db.insert(TABLE_NAME_PLAYER, null, values);
        db.close();
    }

    /* Method to display all the player in the table */
    @SuppressLint("Range")
    public ArrayList<PlayerNames> getAllPlayersByTeam(int teamId) {
        ArrayList<PlayerNames> playersList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                PLAYER_ID,
                TEAM_ID_PLAYER,
                PLAYER_NAME,
                PLAYER_IMG_PATH,
                PLAYER_MATCHES,
                PLAYER_RUNS,
                PLAYER_NOTOUTS,
                PLAYER_BEST_SCORE,
                PLAYER_SR,
                PLAYER_AVERAGE,
                PLAYER_FOURS,
                PLAYER_SIXES,
                PLAYER_THIRTIES,
                PLAYER_FIFTIES,
                PLAYER_HUNDREDS,
                PLAYER_OVERS,
                PLAYER_WICKETS,
                PLAYER_RUNS_BOWL,
                PLAYER_FWICKETS,
                PLAYER_FIFWICKETS
        };
        String whereClause = TEAM_ID_PLAYER + " = ?";
        String[] selectionArgs = {String.valueOf(teamId)};
        Cursor cursor = db.query(TABLE_NAME_PLAYER, columns, whereClause, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                PlayerNames player = new PlayerNames();
                player.setPlayerId(cursor.getInt(cursor.getColumnIndex(PLAYER_ID)));
                player.setTeamId(cursor.getInt(cursor.getColumnIndex(TEAM_ID_PLAYER)));
                player.setPlayerName(cursor.getString(cursor.getColumnIndex(PLAYER_NAME)));
                player.setPlayerImgPath(cursor.getString(cursor.getColumnIndex(PLAYER_IMG_PATH)));
                player.setPlayerMatches(cursor.getInt(cursor.getColumnIndex(PLAYER_MATCHES)));
                player.setPlayerRuns(cursor.getInt(cursor.getColumnIndex(PLAYER_RUNS)));
                player.setPlayerNotOuts(cursor.getInt(cursor.getColumnIndex(PLAYER_NOTOUTS)));
                player.setPlayerBestScore(cursor.getInt(cursor.getColumnIndex(PLAYER_BEST_SCORE)));
                player.setPlayerSR(cursor.getDouble(cursor.getColumnIndex(PLAYER_SR)));
                player.setPlayerAverage(cursor.getDouble(cursor.getColumnIndex(PLAYER_AVERAGE)));
                player.setPlayerFours(cursor.getInt(cursor.getColumnIndex(PLAYER_FOURS)));
                player.setPlayerSixes(cursor.getInt(cursor.getColumnIndex(PLAYER_SIXES)));
                player.setPlayerThirties(cursor.getInt(cursor.getColumnIndex(PLAYER_THIRTIES)));
                player.setPlayerFifties(cursor.getInt(cursor.getColumnIndex(PLAYER_FIFTIES)));
                player.setPlayerHundreds(cursor.getInt(cursor.getColumnIndex(PLAYER_HUNDREDS)));
                player.setPlayerOvers(cursor.getDouble(cursor.getColumnIndex(PLAYER_OVERS)));
                player.setPlayerWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_WICKETS)));
                player.setPlayerRunsBowl(cursor.getInt(cursor.getColumnIndex(PLAYER_RUNS_BOWL)));
                player.setPlayerFoursWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_FWICKETS)));
                player.setPlayerFiveWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_FIFWICKETS)));

                playersList.add(player);
            } while (cursor.moveToNext());

            cursor.close();
        }
        db.close();
        return playersList;
    }

    /* Method to update player name by player id */
    public void updatePlayerNameImageById(int playerId, String newPlayerName,String newImgPath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_NAME, newPlayerName);
        values.put(PLAYER_IMG_PATH, newImgPath);
        String whereClause = PLAYER_ID + " = ?";
        String[] whereArgs = {String.valueOf(playerId)};
        int rowsUpdated = db.update(TABLE_NAME_PLAYER, values, whereClause, whereArgs);
        db.close();
        if (rowsUpdated > 0) {
            Log.d("PLAYER UPDATE: ", "Player name updated");
        }
    }

    public void updatePlayerNameById(int playerId, String newPlayerName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_NAME, newPlayerName);
        String whereClause = PLAYER_ID + " = ?";
        String[] whereArgs = {String.valueOf(playerId)};
        int rowsUpdated = db.update(TABLE_NAME_PLAYER, values, whereClause, whereArgs);
        db.close();
        if (rowsUpdated > 0) {
            Log.d("PLAYER UPDATE: ", "Player name updated");
        }
    }

    /* Method to get only one player from player table */
    @SuppressLint("Range")
    public PlayerNames getPlayerById(int playerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                PLAYER_ID,
                TEAM_ID_PLAYER,
                PLAYER_NAME,
                PLAYER_IMG_PATH,
                PLAYER_MATCHES,
                PLAYER_RUNS,
                PLAYER_NOTOUTS,
                PLAYER_BEST_SCORE,
                PLAYER_SR,
                PLAYER_AVERAGE,
                PLAYER_FOURS,
                PLAYER_SIXES,
                PLAYER_THIRTIES,
                PLAYER_FIFTIES,
                PLAYER_HUNDREDS,
                PLAYER_OVERS,
                PLAYER_WICKETS,
                PLAYER_RUNS_BOWL,
                PLAYER_FWICKETS,
                PLAYER_FIFWICKETS
        };
        String whereClause = PLAYER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(playerId)};
        Cursor cursor = db.query(TABLE_NAME_PLAYER, columns, whereClause, selectionArgs, null, null, null);

        PlayerNames player = null;

        if (cursor != null && cursor.moveToFirst()) {
            player = new PlayerNames();
            player.setPlayerId(cursor.getInt(cursor.getColumnIndex(PLAYER_ID)));
            player.setTeamId(cursor.getInt(cursor.getColumnIndex(TEAM_ID_PLAYER)));
            player.setPlayerName(cursor.getString(cursor.getColumnIndex(PLAYER_NAME)));
            player.setPlayerImgPath(cursor.getString(cursor.getColumnIndex(PLAYER_IMG_PATH)));
            player.setPlayerMatches(cursor.getInt(cursor.getColumnIndex(PLAYER_MATCHES)));
            player.setPlayerRuns(cursor.getInt(cursor.getColumnIndex(PLAYER_RUNS)));
            player.setPlayerNotOuts(cursor.getInt(cursor.getColumnIndex(PLAYER_NOTOUTS)));
            player.setPlayerBestScore(cursor.getInt(cursor.getColumnIndex(PLAYER_BEST_SCORE)));
            player.setPlayerSR(cursor.getDouble(cursor.getColumnIndex(PLAYER_SR)));
            player.setPlayerAverage(cursor.getDouble(cursor.getColumnIndex(PLAYER_AVERAGE)));
            player.setPlayerFours(cursor.getInt(cursor.getColumnIndex(PLAYER_FOURS)));
            player.setPlayerSixes(cursor.getInt(cursor.getColumnIndex(PLAYER_SIXES)));
            player.setPlayerThirties(cursor.getInt(cursor.getColumnIndex(PLAYER_THIRTIES)));
            player.setPlayerFifties(cursor.getInt(cursor.getColumnIndex(PLAYER_FIFTIES)));
            player.setPlayerHundreds(cursor.getInt(cursor.getColumnIndex(PLAYER_HUNDREDS)));
            player.setPlayerOvers(cursor.getDouble(cursor.getColumnIndex(PLAYER_OVERS)));
            player.setPlayerWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_WICKETS)));
            player.setPlayerRunsBowl(cursor.getInt(cursor.getColumnIndex(PLAYER_RUNS_BOWL)));
            player.setPlayerFoursWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_FWICKETS)));
            player.setPlayerFiveWickets(cursor.getInt(cursor.getColumnIndex(PLAYER_FIFWICKETS)));
            cursor.close();
        }
        db.close();
        return player;
    }

    /* Function to delete a player by player_id  in teams table */
    public void deletePlayer(int playerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = PLAYER_ID + " = ?";
        String[] whereArgs = {String.valueOf(playerId)};
        db.delete(TABLE_NAME_PLAYER, whereClause, whereArgs);
        db.close();
    }
}
