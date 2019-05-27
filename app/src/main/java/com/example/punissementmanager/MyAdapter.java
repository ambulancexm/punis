package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objetsjava.Stagiaire;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    ArrayList<Stagiaire> list = new ArrayList<>();

    public MyAdapter(ArrayList<Stagiaire> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cells_view,viewGroup,false);
        return new MyViewHolder(view);
    }
//
//    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Stagiaire stagiaireObjet = list.get(position);
        myViewHolder.bind(stagiaireObjet);
    }
//
    @Override
    public int getItemCount() {
        return list.size();
    }

}


