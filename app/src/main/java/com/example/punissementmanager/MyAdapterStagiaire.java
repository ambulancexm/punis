package com.example.punissementmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objetsjava.Stagiaire;

import java.util.ArrayList;

public class MyAdapterStagiaire extends RecyclerView.Adapter<MyViewHolderStagiaire>{

    ArrayList<Stagiaire> list = new ArrayList<>();

    public MyAdapterStagiaire(ArrayList<Stagiaire> list) {
        this.list = list;
    }

    @Override
    public MyViewHolderStagiaire onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_stagiaire,viewGroup,false);
        return new MyViewHolderStagiaire(view);
    }
//
//    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolderStagiaire myViewHolderStagiaire, int position) {
        Stagiaire stagiaireObjet = list.get(position);
        myViewHolderStagiaire.bind(stagiaireObjet);
    }
//
    @Override
    public int getItemCount() {
        return list.size();
    }

}


