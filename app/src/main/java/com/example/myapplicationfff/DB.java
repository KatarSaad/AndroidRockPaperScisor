/*

package com.example.myapplicationfff;
import com.example.myapplicationfff.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "etudiantsManager";
    private static final String TABLE_ETUDIANTS = "etudiants";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADD = "adress";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ETUDIANTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer l'ancien tableau
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANTS);
        // Créer un nouveau tableau
        onCreate(db);
    }


    void addEtudiant(Etudiant etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, etudiant.getName()); // Nom de l'étudiant
        values.put(KEY_ADD, etudiant.getAdress()); // Addresse de l'étudiant

        // Insérer une ligne
        db.insert(TABLE_ETUDIANTS, null, values);
        //Fermer la connexion à la base de données
        db.close();
    }


    Etudiant getEtudiant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ETUDIANTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_ADD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Etudiant etudiant = new Etudiant(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return etudiant;
    }


    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiantList = new ArrayList<Etudiant>();
        // Sélectionner toute la requête
        String selectQuery = "SELECT  * FROM " + TABLE_ETUDIANTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcourir toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                Etudiant etudiant = new Etudiant();
                etudiant.setID(Integer.parseInt(cursor.getString(0)));
                etudiant.setName(cursor.getString(1));
                etudiant.setAdress(cursor.getString(2));
                // Ajouter l'étudiant à la liste
                etudiantList.add(etudiant);
            } while (cursor.moveToNext());
        }
        // Retourner la liste d'étudiant
        return etudiantList;
    }


    public int updateEtudiant(Etudiant etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, etudiant.getName());
        values.put(KEY_ADD, etudiant.getAdress());
        // Mettre à jours la ligne
        return db.update(TABLE_ETUDIANTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(etudiant.getID()) });
    }


    public void deleteEtudiant(Etudiant etudiant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ETUDIANTS, KEY_ID + " = ?",
                new String[] { String.valueOf(etudiant.getID()) });
        db.close();
    }


    public int getEtudiantsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ETUDIANTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // retourner le nombre
        return cursor.getCount();
    }


}


 */
