package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.objetsjava.Punissement;

import static android.support.v7.widget.RecyclerView.*;

public class MyViewHolder extends ViewHolder {


    private TextView type;
    private TextView date;
    private TextView lieu;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        type= (TextView)itemView.findViewById(R.id.nomPunissement);
        date= (TextView)itemView.findViewById(R.id.datePunissement);
        lieu= (TextView)itemView.findViewById(R.id.lieuPunissement);

    }





    public void bind(Punissement punissementObject) {

        type.setText(punissementObject.getType());
        date.setText(ApplicationManager.getInstance().ConvertirDateEnString(punissementObject.getDate()));
        lieu.setText(punissementObject.getLieu());
    }

}
