package com.example.punissementmanager;

import android.content.res.Resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.objetsjava.Punissement;

import java.util.ArrayList;
import java.util.Date;

import static com.example.punissementmanager.R.*;

public class GestionPunissement extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Resources res;
    private ArrayList<Punissement> listePunissement = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_gestion_punissement);

        recyclerView = findViewById(id.listPunissementRView);
        listePunissement = ApplicationManager.getInstance().ListPunissement(1);


        recyclerView.setAdapter(new MyAdapteurPunissement(listePunissement));
    }

}