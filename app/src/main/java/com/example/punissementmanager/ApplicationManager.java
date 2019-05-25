package com.example.punissementmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import com.objetsjava.*;

import com.bdd.DBManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationManager {

    private static ApplicationManager applicationManager;

    private static DBManager dbManager;

    public static ApplicationManager getInstance() {
        if (applicationManager == null) {
            applicationManager = new ApplicationManager();
        }

        return applicationManager;
    }

    private ApplicationManager () {

    }

    public static void InitialiserApplication (Context context){
        dbManager = new DBManager(context);
    }

    public DBManager getDbManager() {return dbManager;}

    public String ConvertirDateEnString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }

    public Date ConvertirStringEnDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateParse;
        try {
             dateParse = dateFormat.parse(date);
        } catch (ParseException e) {
            e.getMessage();
            dateParse = null;
        }

        return dateParse;
    }

    public Punissement CreerPunissement (int punissement_id) {
        Cursor res = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBPunissement.TABLE_NAME
                + " WHERE " + DBManager.DBPunissement.ID + "=" + punissement_id +";");

        if (res.getCount() == 0) {
            Log.e("error", "pas trouve, punissement_id = " + punissement_id);
            return null;
        }

        Cursor resList = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBStagiairesPunis.TABLE_NAME
        + " WHERE " + DBManager.DBStagiairesPunis.PUNISSEMENT_ID + "=" + punissement_id);

        ArrayList<Integer> stagiairesPunis = new ArrayList<>();

        resList.moveToFirst();
        if (resList.getCount() == 0) {
            while (!resList.isAfterLast()) {
                stagiairesPunis.add(resList.getInt(res.getColumnIndex(DBManager.DBStagiairesPunis.STAGIAIRE_ID)));
                resList.moveToNext();
            }
        }
        resList.close();

        res.moveToFirst();
        if(!res.isAfterLast()) {
            TYPEPUNITION type = TYPEPUNITION.valueOf(res.getString(res.getColumnIndex(DBManager.DBPunissement.TYPE)));

            return new Punissement(punissement_id,
                    type,
                    res.getString(res.getColumnIndex(DBManager.DBPunissement.DESCRIPTION)),
                    ConvertirStringEnDate(res.getString(res.getColumnIndex(DBManager.DBPunissement.DATE))),
                    res.getString(res.getColumnIndex(DBManager.DBPunissement.LIEU)),
                    res.getInt(res.getColumnIndex(DBManager.DBPunissement.FORMATEUR_ID)),
                    stagiairesPunis);
        }
        else {
            Log.e("error", "null");
            return null;
        }
    }

    public void AjouterPunissement (Punissement punissement) {
        ContentValues content = new ContentValues();

        content.put(DBManager.DBPunissement.TYPE, punissement.getType().toString());
        content.put(DBManager.DBPunissement.DESCRIPTION, punissement.getDescription());

        content.put(DBManager.DBPunissement.DATE, ConvertirDateEnString(punissement.getDate()));

        Log.e("verif date", ConvertirDateEnString(punissement.getDate()));

        dbManager.ajouterContentValues(DBManager.DBPunissement.TABLE_NAME, content);
    }

    public Stagiaire CreerStagiaire (int stagiaire_id) {
        Cursor res = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBStagiaire.TABLE_NAME
            + " WHERE " + DBManager.DBStagiaire.ID + "=" + stagiaire_id + ";");
        if (res.getCount() == 0) {
            return null;
        }

        res.moveToFirst();

        if (!res.isAfterLast()) {
            return new Stagiaire(res.getInt(res.getColumnIndex(DBManager.DBStagiaire.ID)),
                    res.getString(res.getColumnIndex(DBManager.DBStagiaire.NOM)),
                    res.getString(res.getColumnIndex(DBManager.DBStagiaire.PRENOM)),
                    res.getBlob(res.getColumnIndex(DBManager.DBStagiaire.PHOTO)),
                    res.getString(res.getColumnIndex(DBManager.DBStagiaire.EMAIL)),
                    res.getString(res.getColumnIndex(DBManager.DBStagiaire.TELEPHONE)),
                    res.getInt(res.getColumnIndex(DBManager.DBStagiaire.SESSION_ID)),
                    false);
        }
        else {
            return null;
        }
    }

    public void AjouterStagiaire (Stagiaire stagiaire) {
        ContentValues content = new ContentValues();

        content.put(DBManager.DBStagiaire.NOM, stagiaire.getNom());
        content.put(DBManager.DBStagiaire.PRENOM, stagiaire.getPrenom());
        content.put(DBManager.DBStagiaire.EMAIL, stagiaire.getEmail());
        content.put(DBManager.DBStagiaire.TELEPHONE, stagiaire.getTelephone());
        // a modifier quand une meilleure solution sera trouvee:
        content.put(DBManager.DBStagiaire.PHOTO, stagiaire.getImage());
        content.put(DBManager.DBStagiaire.SESSION_ID, stagiaire.getSession_id());

        dbManager.ajouterContentValues(DBManager.DBStagiaire.TABLE_NAME, content);
    }

    public Formateur CreerFormateur(int formateur_id) {
        Cursor res = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBFormateur.TABLE_NAME
            + " WHERE " + DBManager.DBFormateur.ID + "=" + formateur_id + ";");

        if (res.getCount() == 0) {
            return  null;
        }

        ArrayList<Integer> sessionIDs = new ArrayList<>();

        Cursor resList = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBSession.TABLE_NAME
                + " WHERE " + DBManager.DBSession.FORMATEUR_ID + "=" + formateur_id);
        if (resList.getCount() != 0) {
            resList.moveToFirst();

            while (!resList.isAfterLast()) {
                    sessionIDs.add(resList.getInt(resList.getColumnIndex(DBManager.DBSession.ID)));
                    resList.moveToNext();
            }
        }
        resList.close();
        res.moveToFirst();

        if (!res.isAfterLast()) {
            return new Formateur(res.getInt(res.getColumnIndex(DBManager.DBFormateur.ID)),
                    res.getString(res.getColumnIndex(DBManager.DBFormateur.NOM)),
                    res.getString(res.getColumnIndex(DBManager.DBFormateur.PRENOM)),
                    res.getString(res.getColumnIndex(DBManager.DBFormateur.USERNAME)),
                    sessionIDs);
        }
        else {
            return null;
        }
    }

    public void AjouterFormateur(Formateur formateur, String motDePasse) {
        ContentValues content = new ContentValues();

        content.put(DBManager.DBFormateur.USERNAME, formateur.getUserName());
        content.put(DBManager.DBFormateur.NOM, formateur.getNom());
        content.put(DBManager.DBFormateur.PRENOM, formateur.getPrenom());
        content.put(DBManager.DBFormateur.MDP, motDePasse);

        dbManager.ajouterContentValues(DBManager.DBFormateur.TABLE_NAME, content);
    }

    public void AjouterSession (Session session) {
        ContentValues content = new ContentValues();

        content.put(DBManager.DBSession.NOM, session.getNom());
        content.put(DBManager.DBSession.FORMATEUR_ID, session.getFormateur_id());

        dbManager.ajouterContentValues(DBManager.DBSession.TABLE_NAME, content);
    }

    public Session CreerSession (int session_id) {

        Cursor res = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBSession.TABLE_NAME
                + " WHERE " + DBManager.DBSession.ID + "=" + session_id + ";");
        if (res.getCount() == 0 ) {
            return null;
        }



        ArrayList<Integer> stagiairesIDs = new ArrayList<>();

        Cursor resList = dbManager.SelectRequest("SELECT * FROM " + DBManager.DBStagiaire.TABLE_NAME
                + " WHERE " + DBManager.DBStagiaire.SESSION_ID + "=" + session_id + ";");

        if (resList.getCount() != 0) {
            resList.moveToFirst();

            while (!resList.isAfterLast()) {
                stagiairesIDs.add(resList.getInt(resList.getColumnIndex(DBManager.DBStagiaire.ID)));
                resList.moveToNext();
            }
        }

        res.moveToFirst();

        return new Session(res.getInt(res.getColumnIndex(DBManager.DBSession.ID)),
                stagiairesIDs,
                res.getString(res.getColumnIndex(DBManager.DBSession.NOM)),
                res.getInt(res.getColumnIndex(DBManager.DBSession.FORMATEUR_ID))
        );
    }

    public void Test() {

        ArrayList<Integer> formateurSession = new ArrayList<>();
        formateurSession.add(1);
        formateurSession.add(3);

        Formateur formateur1 = new Formateur(1, "theboss", "Rault", "Nicolas", formateurSession);

        ArrayList<Integer> formateurSession2 = new ArrayList<>();
        formateurSession.add(2);

        Formateur formateur2 = new Formateur(2, "test", "Nom", "Prenom", formateurSession2);

        ArrayList<Integer> stagiairesSession1 = new ArrayList<>();
        ArrayList<Integer> stagiairesSession2 = new ArrayList<>();
        ArrayList<Integer> stagiairesSession3 = new ArrayList<>();

        Stagiaire stag1 = new Stagiaire(1, "Morane", "Bob", null, "email", "0600000000", 1, false);
        Stagiaire stag2 = new Stagiaire(2, "test1", "stag1", null, "email", "0600000000", 1, true);
        Stagiaire stag3 = new Stagiaire(3, "test2", "stag2", null, "email", "0600000000", 1, false);

        stagiairesSession1.add(stag1.getId());
        stagiairesSession1.add(stag2.getId());
        stagiairesSession1.add(stag3.getId());

        Stagiaire stag4 = new Stagiaire(4, "test3", "stag3", null, "email", "0600000000", 1, true);
        Stagiaire stag5 = new Stagiaire(5, "test4", "stag4", null, "email", "0600000000", 1, false);

        stagiairesSession2.add(stag4.getId());
        stagiairesSession2.add(stag5.getId());

        Stagiaire stag6 = new Stagiaire(6, "test5", "stag5", null, "email", "0600000000", 1, true);
        Stagiaire stag7 = new Stagiaire(7, "test6", "stag6", null, "email", "0600000000", 1, true);

        stagiairesSession3.add(stag6.getId());
        stagiairesSession3.add(stag7.getId());

        Session session1 = new Session(1, stagiairesSession1, "session 1", 1);
        Session session2 = new Session(2, stagiairesSession2, "dev en salle", 2);
        Session session3 = new Session(3, stagiairesSession3, "admin reseau", 1);

        ArrayList<Integer> stagiairesPunis1 = new ArrayList<>();
        ArrayList<Integer> stagiairesPunis2 = new ArrayList<>();
        ArrayList<Integer> stagiairesPunis3 = new ArrayList<>();

        stagiairesPunis1.add(stag2.getId());
        stagiairesPunis2.add(stag4.getId());
        stagiairesPunis3.add(stag6.getId());
        stagiairesPunis3.add(stag7.getId());

        Date dateTest = new Date();

        Punissement punissement1 = new Punissement(1, TYPEPUNITION.CUISINE, "cuisine un gateau", dateTest, "Cuisine LDNR", 1, stagiairesPunis1);
        Punissement punissement2 = new Punissement(2, TYPEPUNITION.DEVOIR, "faire une appli qui gere les punissements", dateTest, "n'importe", 2, stagiairesPunis2);
        Punissement punissement3 = new Punissement(3, TYPEPUNITION.TACHE, "repeindre les murs", dateTest, "Chez ouam", 1, stagiairesPunis3);

        AjouterFormateur(formateur1, "1234");

        AjouterFormateur(formateur2, "4321");

        AjouterSession(session1);
        AjouterSession(session2);
        AjouterSession(session3);

        AjouterStagiaire(stag1);
        AjouterStagiaire(stag2);
        AjouterStagiaire(stag3);
        AjouterStagiaire(stag4);
        AjouterStagiaire(stag5);
        AjouterStagiaire(stag6);
        AjouterStagiaire(stag7);

        AjouterPunissement(punissement1);
        AjouterPunissement(punissement2);
        AjouterPunissement(punissement3);

        Formateur testFormateur = CreerFormateur(1);
        Session testSession = CreerSession(1);
        Stagiaire testStagiaire = CreerStagiaire(2);
        Punissement testPunissement = CreerPunissement(1);

        Log.e("testFormateur", testFormateur.getNom());
        Log.e("testSession", testSession.getNom());
        Log.e("testFormateur", testStagiaire.getNom());
        if (testPunissement != null)
            Log.e("testFormateur", testPunissement.getType().toString());
        else
            Log.e("testPunissement", "est null");
    }

}
