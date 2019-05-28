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
    Intent RegisterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        ApplicationManager.InitialiserApplication(this);

        final EditText Username = (EditText) findViewById(R.id.Username);
        final EditText Password = (EditText) findViewById(R.id.Password);
        final Button buttonConnexion = (Button) findViewById(R.id.buttonConnexion);


        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _Username = Username.getText().toString();
                String _Password = Password.getText().toString();

                ApplicationManager.getInstance().setFormateurConnect(ApplicationManager.getInstance().FormateurConnect(_Username, _Password));

                if (ApplicationManager.getInstance().getFormateurConnect() != null) {
                   // Toast.makeText(getApplicationContext(), "vous êtes connecté", Toast.LENGTH_LONG).show();
                    Intent testView = new Intent(ConnexionActivity.this,GestionPunissement.class);
                    startActivity(testView);
                } else {
                    Toast.makeText(getApplicationContext(), "Password ou username invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onRegisterButton(View view){
        Intent RegisterView = new Intent(this,ListCheckedActivity.class);
        startActivity(RegisterView);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id){
            case R.id.stagiaire:
                RegisterView = new Intent(this,StagiaireActivity.class);
                startActivity(RegisterView);
                return true;
            case R.id.punis:
                //RegisterView = new Intent(this,RegisterActivity.class);
                //startActivity(RegisterView);
                return true;
            case R.id.formateur:
                RegisterView = new Intent(this,GestionPunissement.class);
                startActivity(RegisterView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onLoginConnection(View view) {
        RegisterView = new Intent(getApplicationContext(),StagiaireActivity.class);
        Log.e("**************** ", "passé!!!");
        startActivity(RegisterView);


    }
}
