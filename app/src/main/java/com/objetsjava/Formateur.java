package com.objetsjava;

import java.util.ArrayList;
import java.util.List;

public class Formateur {

    private int id;
    private String userName;
    private String nom;
    private String prenom;
    //private String motDePasse;
    private ArrayList<Integer> sessionIDs;

    public Formateur(int id, String userName, String nom, String prenom, ArrayList<Integer> sessionIDs) {
        this.id = id;
        this.userName = userName;
        this.nom = nom;
        this.prenom = prenom;
        this.sessionIDs = sessionIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<Integer> getSessionIDs() {
        return sessionIDs;
    }

    public void setSessionIDs(ArrayList<Integer> sessionIDs) {
        this.sessionIDs = sessionIDs;
    }

    public void AddSessionId(Integer session_id) {
        sessionIDs.add(session_id);
    }

    public void RemoveSessionId(Integer session_id) {
        sessionIDs.remove(session_id);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
