package com.example.punissementmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.objetsjava.Punissement;

import java.util.ArrayList;

public class GestionPunissement extends AppCompatActivity {

    private ArrayList<Punissement>listePunissement = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_punissement);

        listePunissement = ApplicationManager.getInstance().ListPunissement(1);

    }



}
