package com.objetsjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.bdd.DBManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

public class Stagiaire implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private byte[] image;
    private String email;
    private String telephone;
    private int session_id;
    private Boolean puni;

    private static DBManager dbManager;

    public  Stagiaire(){}

    public Stagiaire(int id, String nom, String prenom, byte[] image, String email, String telephone, int session_id, Boolean puni) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.email = email;
        this.telephone = telephone;
        this.session_id = session_id;
        this.puni = puni;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    // Permet de créer une image à partir du byte[] image du stagiaire
    public Bitmap ConvertBlobToImage() {
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        return BitmapFactory.decodeStream(imageStream);
    }

    // Permet de convertir une image en byte[] pour stocker dans la variable image, puis dans la base de donnée en tant que blob
    public byte[] ConvertBitmapToBlob(Bitmap image) {
        ByteArrayOutputStream blobStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, blobStream);
        return blobStream.toByteArray();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public Boolean getPuni() {
        return puni;
    }

    public void setPuni(Boolean puni) {
        this.puni = puni;
    }
}
