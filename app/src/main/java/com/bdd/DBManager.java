package com.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.punissementmanager.ApplicationManager;

public class DBManager extends SQLiteOpenHelper {

    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead = this.getReadableDatabase();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gestionPunissement";

    public DBManager (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbWrite = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBFormateur.CREATE_TABLE);
        db.execSQL(DBSession.CREATE_TABLE);
        db.execSQL(DBPunissement.CREATE_TABLE);
        db.execSQL(DBStagiaire.CREATE_TABLE);
        db.execSQL(DBStagiairesPunis.CREATE_TABLE);
        db.execSQL(DBPunissement.AjoutTest2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void ajouterContentValues(String nomTable, ContentValues content) {
        try {
            dbWrite.insertOrThrow(nomTable, null, content);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Cursor SelectRequest(String request) {
        Cursor res = dbRead.rawQuery(request, null);
        return res;
    }

    // classes internes pour stocker les commandes sql dans des constantes

    // constantes de la table stagiaire
    public static class DBStagiaire implements BaseColumns {
        public static final String TABLE_NAME = "stagiaire";
        public static final String ID = "stagiaire_id";
        public static final String NOM = "nom";
        public static final String PRENOM = "prenom";
        public static final String PHOTO = "photo";
        public static final String EMAIL = "email";
        public static final String TELEPHONE = "telephone";
        public static final String SESSION_ID = "session_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER primary key, "
                + NOM + " VARCHAR NOT NULL, "
                + PRENOM + " VARCHAR NOT NULL, "
                + PHOTO + " BLOB, "
                + EMAIL + " VARCHAR, "
                + TELEPHONE + " VARCHAR, "
                + SESSION_ID + " INTEGER NOT NULL, " +
                "CONSTRAINT fk_session FOREIGN KEY(" + DBSession.ID +") REFERENCES " + DBSession.TABLE_NAME +"(" + DBSession.ID + ")); ";
    }

    public static class DBPunissement implements BaseColumns {
        public static final String TABLE_NAME = "punissement";
        public static final String ID = "punissement_id";
        public static final String TYPE = "type";
        public static final String DATE = "date";
        public static final String LIEU = "lieu";
        public static final String DESCRIPTION = "description";
        public static final String FORMATEUR_ID = "formateur_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER primary key, "
                + TYPE + " VARCHAR NOT NULL, "
                + DATE + " VARCHAR NOT NULL, "
                + LIEU + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + FORMATEUR_ID + " INTEGER NOT NULL, " +
                "CONSTRAINT fk_formateur FOREIGN KEY(" + DBFormateur.ID +") REFERENCES " + DBFormateur.TABLE_NAME +"(" + DBFormateur.ID + ")); ";

        public static final String AjoutTest = "INSERT INTO " + TABLE_NAME + " ("
                + TYPE +", " + DATE + ", " + LIEU + ", " + DESCRIPTION + ", " +  FORMATEUR_ID +")" +
                " VALUES(cuisine, lundi, test, testitest, 1);";
        public static final String AjoutTest2 = "INSERT INTO punissement (type, date, lieu, description, formateur_id) VALUES ('CUISINE', 'lundi', 'test', 'testitest', '1');";
    }

    public static class DBSession implements BaseColumns {
        public static final String TABLE_NAME = "session";
        public static final String ID = "session_id";
        public static final String NOM = "nom";
        public static final String FORMATEUR_ID = "formateur_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER primary key, "
                + NOM + " VARCHAR NOT NULL, "
                + FORMATEUR_ID + " INTEGER NOT NULL, " +
                "CONSTRAINT fk_formateur FOREIGN KEY(" + DBFormateur.ID + ") REFERENCES " + DBFormateur.TABLE_NAME +"(" + DBFormateur.ID + ")); ";
    }

    public static class DBFormateur implements BaseColumns {
        public static final String TABLE_NAME = "formateur";
        public static final String ID = "formateur_id";
        public static final String USERNAME = "username";
        public static final String NOM = "nom";
        public static final String PRENOM = "prenom";
        public static final String MDP = "motdepasse";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID + " INTEGER primary key, "
                + USERNAME + " VARCHAR NOT NULL,"
                + NOM + " VARCHAR NOT NULL, "
                + PRENOM + " VARCHAR NOT NULL, "
                + MDP + " VARCHAR); ";
    }

    public static class DBStagiairesPunis implements BaseColumns {
        public static final String TABLE_NAME = "punis";
        public static final String PUNISSEMENT_ID = "punissement_id";
        public static final String STAGIAIRE_ID = "stagiaire_id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + PUNISSEMENT_ID + " INTEGER NOT NULL, "
                + STAGIAIRE_ID + " INTEGER NOT NULL, " +
                "CONSTRAINT fk_punissement FOREIGN KEY(" + DBPunissement.ID + ") REFERENCES " + DBPunissement.TABLE_NAME + "(" + DBPunissement.ID + "), " +
                "CONSTRAINT fk_stagiaire FOREIGN KEY(" + DBStagiaire.ID + ") REFERENCES " + DBStagiaire.TABLE_NAME + "(" + DBStagiaire.ID + ")); ";
    }
}

