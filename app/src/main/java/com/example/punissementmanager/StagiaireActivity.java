package com.example.punissementmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.objetsjava.Stagiaire;


public class StagiaireActivity extends AppCompatActivity {



    private ApplicationManager Am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagiaire);

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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
