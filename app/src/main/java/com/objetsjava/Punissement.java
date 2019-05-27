package com.objetsjava;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Punissement {
    private int id;
    private TYPEPUNITION type;
    private String description;
    private Date date;
    private String lieu;
    private int formateur_id;
    private ArrayList<Integer> stagiairesPunis;

    public Punissement(int id, TYPEPUNITION type, String description, Date date, String lieu, int formateur_id, ArrayList<Integer> stagiairesPunis) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.formateur_id = formateur_id;
        this.stagiairesPunis = stagiairesPunis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TYPEPUNITION getType() {
        return type;
    }

    public void setType(TYPEPUNITION type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getFormateur_id() {
        return formateur_id;
    }

    public void setFormateur_id(int formateur_id) {
        this.formateur_id = formateur_id;
    }

    public ArrayList<Integer> getStagiairesPunis() {
        return stagiairesPunis;
    }

    public void setStagiairesPunis(ArrayList<Integer> stagiairesPunis) {
        this.stagiairesPunis = stagiairesPunis;
    }
}
