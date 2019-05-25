package com.objetsjava;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private int id;
    private ArrayList<Integer> stagiairesIDS;
    private String nom;
    private int formateur_id;

    public Session(int id, ArrayList<Integer> stagiairesIDS, String nom, int formateur_id) {
        this.id = id;
        this.stagiairesIDS = stagiairesIDS;
        this.nom = nom;
        this.formateur_id = formateur_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getStagiairesIDS() {
        return stagiairesIDS;
    }

    public void setStagiairesIDS(ArrayList<Integer> stagiairesIDS) {
        this.stagiairesIDS = stagiairesIDS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getFormateur_id() {
        return formateur_id;
    }

    public void setFormateur_id(int formateur_id) {
        this.formateur_id = formateur_id;
    }
}
