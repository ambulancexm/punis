package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    List<*****objet*******> list;

    public MyAdapter(List<****objet*****> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new MyViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        CapitalObject capitalObject = list.get(position);
        myViewHolder.bind(capitalObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}


