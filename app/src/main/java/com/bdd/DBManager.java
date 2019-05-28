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
import com.objetsjava.Formateur;

import java.text.Normalizer;
import java.util.Date;

public class DBManager extends SQLiteOpenHelper {

    private static SQLiteDatabase dbWrite;
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
        content.clear();
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

    public void InitialiserDonnees() {

        ContentValues content = new ContentValues();

        content.put(DBFormateur.USERNAME, "theboss");
        content.put(DBFormateur.PRENOM, "Marc");
        content.put(DBFormateur.NOM, "Abeille");
        content.put(DBFormateur.MDP, "1234");

        ajouterContentValues(DBFormateur.TABLE_NAME, content);

        content.put(DBFormateur.USERNAME, "formateur");
        content.put(DBFormateur.PRENOM, "Prenom");
        content.put(DBFormateur.NOM, "Nom");
        content.put(DBFormateur.MDP, "4321");

        ajouterContentValues(DBFormateur.TABLE_NAME, content);

        Date date = new Date();

        content.put(DBPunissement.TYPE, "CUISINE");
        content.put(DBPunissement.LIEU, "LDNR");
        content.put(DBPunissement.DESCRIPTION, "Ramène un gâteau fait maison");
        content.put(DBPunissement.DATE, ApplicationManager.getInstance().ConvertirDateEnString(date));
        content.put(DBPunissement.FORMATEUR_ID, 1);

        ajouterContentValues(DBManager.DBPunissement.TABLE_NAME, content);

        content.put(DBPunissement.TYPE, "DEVOIR");
        content.put(DBPunissement.LIEU, "LDNR");
        content.put(DBPunissement.DESCRIPTION, "Créer une application qui gère les punissements");
        content.put(DBPunissement.DATE, ApplicationManager.getInstance().ConvertirDateEnString(date));
        content.put(DBPunissement.FORMATEUR_ID, 1);

        ajouterContentValues(DBManager.DBPunissement.TABLE_NAME, content);

        content.put(DBPunissement.TYPE, "TACHE");
        content.put(DBPunissement.LIEU, "LDNR");
        content.put(DBPunissement.DESCRIPTION, "Faire la vaissele");
        content.put(DBPunissement.DATE, ApplicationManager.getInstance().ConvertirDateEnString(date));
        content.put(DBPunissement.FORMATEUR_ID, 2);

        ajouterContentValues(DBManager.DBPunissement.TABLE_NAME, content);

        content.put(DBManager.DBSession.NOM, "Développeur C/C++/Java à distance");
        content.put(DBManager.DBSession.FORMATEUR_ID, 1);

        ajouterContentValues(DBManager.DBSession.TABLE_NAME, content);

        content.put(DBManager.DBSession.NOM, "Session de nuls");
        content.put(DBManager.DBSession.FORMATEUR_ID, 2);

        ajouterContentValues(DBManager.DBSession.TABLE_NAME, content);

        byte[] parDefaut = null;

        content.put(DBManager.DBStagiaire.NOM, "Rault");
        content.put(DBManager.DBStagiaire.PRENOM, "Nicolas");
        content.put(DBManager.DBStagiaire.EMAIL, "nicopfrault@hotmail.com");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000000");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 1);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "Chargé");
        content.put(DBManager.DBStagiaire.PRENOM, "Sonia");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000000");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 1);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "Conte");
        content.put(DBManager.DBStagiaire.PRENOM, "Thibault");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000000");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 1);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "Bereziat");
        content.put(DBManager.DBStagiaire.PRENOM, "Thomas");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000000");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 1);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "Cannillo");
        content.put(DBManager.DBStagiaire.PRENOM, "Pauline");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000000");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 1);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "NomTest1");
        content.put(DBManager.DBStagiaire.PRENOM, "PrénomTest1");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse1@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000001");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 2);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "NomTest2");
        content.put(DBManager.DBStagiaire.PRENOM, "PrénomTest2");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse2@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000002");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 2);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "NomTest3");
        content.put(DBManager.DBStagiaire.PRENOM, "PrénomTest3");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse3@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000003");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 2);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBManager.DBStagiaire.NOM, "NomTest4");
        content.put(DBManager.DBStagiaire.PRENOM, "PrénomTest4");
        content.put(DBManager.DBStagiaire.EMAIL, "adresse4@truc.machin");
        content.put(DBManager.DBStagiaire.TELEPHONE, "0600000004");
        content.put(DBManager.DBStagiaire.PHOTO, parDefaut);
        content.put(DBManager.DBStagiaire.SESSION_ID, 2);

        ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);

        content.put(DBStagiairesPunis.PUNISSEMENT_ID, 1);
        content.put(DBStagiairesPunis.STAGIAIRE_ID, 2);

        ajouterContentValues(DBStagiairesPunis.TABLE_NAME, content);

        content.put(DBStagiairesPunis.PUNISSEMENT_ID, 2);
        content.put(DBStagiairesPunis.STAGIAIRE_ID, 4);

        ajouterContentValues(DBStagiairesPunis.TABLE_NAME, content);

        content.put(DBStagiairesPunis.PUNISSEMENT_ID, 2);
        content.put(DBStagiairesPunis.STAGIAIRE_ID, 5);

        ajouterContentValues(DBStagiairesPunis.TABLE_NAME, content);

        content.put(DBStagiairesPunis.PUNISSEMENT_ID, 3);
        content.put(DBStagiairesPunis.STAGIAIRE_ID, 6);

        ajouterContentValues(DBStagiairesPunis.TABLE_NAME, content);
    }
}

