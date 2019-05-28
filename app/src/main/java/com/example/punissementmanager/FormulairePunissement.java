package com.example.punissementmanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FormulairePunissement extends Activity {

    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaire_punition);

        spinner = (Spinner) findViewById(R.id.spinner);
        List exempleList = new ArrayList();
        exemple.add("Plat salé");
        exemple.add("Plat sucré");
        exemple.add("Tâche à accomplir");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exempleList);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }
}
