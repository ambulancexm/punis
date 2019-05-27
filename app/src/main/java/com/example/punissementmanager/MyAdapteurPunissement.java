package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objetsjava.Punissement;

import java.util.List;

public class MyAdapteurPunissement extends RecyclerView.Adapter<MyViewHolder>{

    List<Punissement> punissementList;

    public MyAdapteurPunissement(List<Punissement>punissementList){
        this.punissementList = punissementList;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listPunissement_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Punissement punissementObject = punissementList.get(position);
        myViewHolder.bind(punissementObject);

    }

    @Override
    public int getItemCount() {
        return punissementList.size();
    }
}
