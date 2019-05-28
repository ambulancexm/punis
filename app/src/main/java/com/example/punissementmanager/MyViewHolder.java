package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.objetsjava.Stagiaire;

import java.sql.Blob;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private Integer id;
    private TextView nom;
    private TextView prenom;
    private Blob photo;
    private TextView email;
    private TextView telephone;
    private Integer session_id;


    public MyViewHolder(View itemView) {
        super(itemView);

        nom = (TextView) itemView.findViewById(R.id.textNomStagiaire);
        prenom = (TextView) itemView.findViewById(R.id.textPrenomStagiare);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un CapitalObject
    public void bind(Stagiaire StagiaireObject){
        //id.(StagiaireObject.getText());
        nom.setText(StagiaireObject.getNom());
        prenom.setText(StagiaireObject.getPrenom());
        //photo.setBytes(StagiaireObject.getImage());
        //email.setText(StagiaireObject.getEmail());
        //telephone.setText(StagiaireObject.getTelephone());
        //session_id.intValue(StagiaireObject.getSession_id());

    }
}
