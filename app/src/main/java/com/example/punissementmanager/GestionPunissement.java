package com.example.punissementmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_punissement, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent RegisterView;
        int id = item.getItemId();


        switch (id){
            case R.id.add_punissement:
                RegisterView = new Intent(this,StagiaireActivity.class);
                startActivity(RegisterView);
                return true;
            case R.id.modif_punissement:
                RegisterView = new Intent(this,RegisterActivity.class);
                startActivity(RegisterView);
                return true;
            case R.id.delete_punissement:
                RegisterView = new Intent(this,GestionPunissement.class);
                startActivity(RegisterView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
