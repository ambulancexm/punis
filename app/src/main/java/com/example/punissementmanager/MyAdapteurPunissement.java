package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.objetsjava.Punissement;

import java.util.List;

public class MyAdapteurPunissement extends RecyclerView.Adapter<MyPunissementHolder>{

    List<Punissement> punissementList;

    public MyAdapteurPunissement(List<Punissement>punissementList){
        this.punissementList = punissementList;
    }

    public MyPunissementHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listpunissement_view,viewGroup,false);
        return new MyPunissementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPunissementHolder myViewHolder, int position) {

        Punissement punissementObject = punissementList.get(position);
        myViewHolder.bind(punissementObject);

    }

    @Override
    public int getItemCount() {
        return punissementList.size();
    }
}
