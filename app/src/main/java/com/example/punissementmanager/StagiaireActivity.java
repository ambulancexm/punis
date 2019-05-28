package com.example.punissementmanager;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bdd.DBManager;
import com.objetsjava.Stagiaire;

import java.util.ArrayList;


public class StagiaireActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Stagiaire> stagiareList;
    private MyAdapter myAdapter;
    private Resources res;
    private DBManager db;
    private ApplicationManager Am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagiaire);
        res = getResources();

        // objet. initialisation
        recyclerView = findViewById(R.id.recyclerView);
        int displayMode = res.getConfiguration().orientation;

        // transformer avec le stagiaire
        /*capitalsList = CapitalObject.getCapitalsList();
        myAdapter = new MyAdapter(capitalsList);
        recyclerView.setAdapter(myAdapter);
        ListSession.get(groupPosition).getStagiairesIDS().get(childPosition);
*/
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
                Toast.makeText(getApplicationContext(),"Item 1 Selected", Toast.LENGTH_LONG).show();

                return true;
            case R.id.modif_stagiaire:
                Toast.makeText(getApplicationContext(),"Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete_stagiaire:
                Toast.makeText(getApplicationContext(),"Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
