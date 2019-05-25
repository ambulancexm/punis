package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewView;
    private ImageView imageView;

    public MyViewHolder(View itemView) {
        super(itemView);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un CapitalObject
    public void bind(******obgjet**** StagiaireObject){
        textViewView.setText(StagiaireObject.getText());


    }
}
