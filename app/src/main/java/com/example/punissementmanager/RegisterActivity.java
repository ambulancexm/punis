package com.example.punissementmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bdd.DBManager;
import com.objetsjava.Formateur;

public class RegisterActivity extends AppCompatActivity {

    Cursor cursor;
    SQLiteDatabase db;
    DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nom = (EditText) findViewById(R.id.nom);
        final EditText prenom = (EditText) findViewById(R.id.prenom);
        final EditText password = (EditText) findViewById(R.id.Password);
        final EditText username = (EditText) findViewById(R.id.Username);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        manager = new DBManager(this);
        db = manager.getReadableDatabase();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _nom = nom.getText().toString();
                String _prenom = prenom.getText().toString();
                String _password = password.getText().toString();
                String _username = username.getText().toString();

            }
        });
    }



}
