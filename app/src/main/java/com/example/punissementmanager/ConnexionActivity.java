package com.example.punissementmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnexionActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        ApplicationManager.InitialiserApplication(this);
        ApplicationManager.getInstance().Test();

        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        buttonConnexion = (Button) findViewById(R.id.buttonConnexion);
    }

    public void onRegisterButton(View view){
        Intent RegisterView = new Intent(this,StagiaireActivity.class);
        startActivity(RegisterView);
    }




    public void onLoginConnection(View view) {
        Intent RegisterView = new Intent(this,StagiaireActivity.class);
        startActivity(RegisterView);
    }
}
