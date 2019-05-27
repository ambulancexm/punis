package com.example.punissementmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bdd.DBManager;

public class ConnexionActivity extends AppCompatActivity {

    Cursor cursor;
    SQLiteDatabase db;
    DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        ApplicationManager.InitialiserApplication(this);

        final EditText Username = (EditText) findViewById(R.id.Username);
        final EditText Password = (EditText) findViewById(R.id.Password);
        final Button buttonConnexion = (Button) findViewById(R.id.buttonConnexion);

        manager = new DBManager(this);
        db = manager.getReadableDatabase();

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _Username = Username.getText().toString();
                String _Password = Password.getText().toString();

                ApplicationManager.getInstance().setFormateurConnect(ApplicationManager.getInstance().FormateurConnect(_Username, _Password));

                if (ApplicationManager.getInstance().getFormateurConnect() != null) {
                    Toast.makeText(getApplicationContext(), "vous êtes connecté", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Password ou username invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onRegisterButton(View view){
        Intent RegisterView = new Intent(this,ListActivity.class);
        startActivity(RegisterView);
    }
}
