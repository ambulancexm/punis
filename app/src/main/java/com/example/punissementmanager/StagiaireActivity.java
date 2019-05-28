package com.example.punissementmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class StagiaireActivity extends AppCompatActivity {

    final String stagiaire=null;
    private TextView nomStagiaire;
    private TextView prenomStagiaire;
    private TextView telephoneStagiaire;
    private TextView emailStagiaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_stagiaire);
        nomStagiaire = (TextView) findViewById(R.id.Nom_Stagiaire);
        nomStagiaire.setText(ApplicationManager.getInstance().StagiaireSelectionner.getNom());
        prenomStagiaire = (TextView) findViewById(R.id.Prenom_Stagiaire);
        prenomStagiaire.setText(ApplicationManager.getInstance().StagiaireSelectionner.getPrenom());
        telephoneStagiaire = (TextView) findViewById(R.id.Telephone_Stagiaire);
        telephoneStagiaire.setText(ApplicationManager.getInstance().StagiaireSelectionner.getTelephone());
        emailStagiaire = (TextView) findViewById(R.id.Email_Stagiaire);
        emailStagiaire.setText(ApplicationManager.getInstance().StagiaireSelectionner.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stagiaire, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        switch (id){
            case R.id.add_stagiaire:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.modif_stagiaire:
                Toast.makeText(getApplicationContext(),"Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
