package com.example.punissementmanager;

import android.content.Intent;
import android.content.res.Resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        listePunissement = ApplicationManager.getInstance().ListPunissement(ApplicationManager.getInstance().getFormateurConnect().getId());

        Log.e("element liste",Integer.toString(listePunissement.size()));
        for(Punissement P: listePunissement){
            Log.e("test", P.getDescription());
        }


        recyclerView.setAdapter(new MyAdapteurPunissement(listePunissement));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_punissement, menu);
        return true;
    }

   // public void addPunissement(){

      //  Intent testView = new Intent(GestionPunissement.this,FormulairePunissement.class);
      //  startActivity(testView);
   // }
}