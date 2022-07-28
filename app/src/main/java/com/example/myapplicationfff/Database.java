package com.example.myapplicationfff;
import com.example.myapplicationfff.Score;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "xogame";
    private static final String TABLE_PLAYERS = "players";
    private static final String TABLE_SCORES = "scores";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";
    private static final String KEY_PLAYER = "name";
    private static final String KEY_AI = "ai";
    private static final String KEY_ME = "me";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE2 = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORES + "("+
                "idS INTEGER PRIMARY KEY ," + KEY_NAME + " TEXT," + KEY_ME + " INTEGER,"
                + KEY_AI + " INTEGER" + ")";

        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE2);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer l'ancien tableau
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        // Créer un nouveau tableau
        onCreate(db);
    }


    void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName()); // Nom de l'étudiant
        values.put(KEY_SCORE, player.getScore()); // Addresse de l'étudiant

        // Insérer une ligne
        db.insert(TABLE_PLAYERS, null, values);
        //Fermer la connexion à la base de données
        db.close();
    }


    Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[] { KEY_ID,
                        KEY_NAME, KEY_SCORE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return player;
    }



    public List<Player> getAllPlayers() {

        List<Player> playerList = new ArrayList<Player>();
        // Sélectionner toute la requête
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP TABLE "+TABLE_PLAYERS);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcourir toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setID(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setScore(cursor.getString(2));
                // Ajouter l'étudiant à la liste
                playerList.add(player);
            } while (cursor.moveToNext());
        }
        // Retourner la liste d'étudiant
        return playerList;
    }


    public int updatePlayer(Player etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, etudiant.getName());
        values.put(KEY_SCORE, etudiant.getScore());
        // Mettre à jours la ligne
        return db.update(TABLE_PLAYERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(etudiant.getID()) });
    }


    public void deletePlayer(Player etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[] { String.valueOf(etudiant.getID()) });
        db.close();
    }


    public int getPlayersCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count=cursor.getCount();
        cursor.close();

        // retourner le nombre
        return count;
    }
    public List<Score> getAllScores() {
        List<Score> scoresList = new ArrayList<Score>();
        // Sélectionner toute la requête
        String selectQuery = "SELECT  * FROM " + TABLE_SCORES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
           // db.execSQL("drop table scores");
        // Parcourir toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setName(cursor.getString(1));

                score.setMe(Integer.parseInt(cursor.getString(2)));
                score.setAi(Integer.parseInt(cursor.getString(3)));

                // Ajouter l'étudiant à la liste
                scoresList.add(score);
            } while (cursor.moveToNext());
        }
        // Retourner la liste d'étudiant
        return scoresList;
    }
    public List<Score> getPlayerScores(String name) {
        List<Score> scoresList = new ArrayList<Score>();
        // Sélectionner toute la requête
        String selectQuery = "SELECT  * FROM " + TABLE_SCORES+" " +
                "where  name='"+name+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
       // db.execSQL("drop table scores");

        // Parcourir toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setID(Integer.parseInt(cursor.getString(0)));

                score.setName(cursor.getString(1));
                score.setAi(Integer.parseInt(cursor.getString(3)));
                score.setMe(Integer.parseInt(cursor.getString(2)));

                // Ajouter l'étudiant à la liste
                scoresList.add(score);
            } while (cursor.moveToNext());
        }
        // Retourner la liste d'étudiant
        return scoresList;
    }

    public int getScoreCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_SCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count=cursor.getCount();
        cursor.close();

        // retourner le nombre
        return count;
    }
    public int getWinCount(String player) {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_SCORES +" where me>ai and name= '"+player+"'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count=cursor.getCount();
        cursor.close();

        // retourner le nombre
        return count;


    }
    public int getLossCount(String player) {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_SCORES + " where me<ai and name= '"+player+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count=cursor.getCount();
        cursor.close();

        // retourner le nombre
        return count;
    }
    void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
         //db.execSQL("drop table  scores");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName()); // Nom de l'étudiant
        values.put(KEY_AI, score.getAi());
        values.put(KEY_ME,score.getMe());
        // Insérer une ligne
        db.insert(TABLE_SCORES, null, values);
        //Fermer la connexion à la base de données
        db.close();
    }


}
