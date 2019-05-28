package com.example.punissementmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.objetsjava.Punissement;

import static android.support.v7.widget.RecyclerView.*;

public class MyPunissementHolder extends ViewHolder {


    private TextView type;
    private TextView date;
    private TextView lieu;

    public MyPunissementHolder(@NonNull View itemView) {
        super(itemView);

        type= (TextView)itemView.findViewById(R.id.nom_punissement);
        date= (TextView)itemView.findViewById(R.id.date_punissement);
        lieu= (TextView)itemView.findViewById(R.id.lieu_punissement);

    }


    public void bind(Punissement punissementObject) {

        type.setText(punissementObject.getType().toString());
        date.setText(ApplicationManager.getInstance().ConvertirDateEnString(punissementObject.getDate()));
        lieu.setText(punissementObject.getLieu());
    }

}
