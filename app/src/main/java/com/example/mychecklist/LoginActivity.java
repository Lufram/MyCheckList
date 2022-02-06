package com.example.mychecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mychecklist.db.DbController;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private DbController dbController;
    private int sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionId = 0;
        dbController = new DbController(this,sessionId);


        // ocultar Action Bar
        getSupportActionBar().hide();

        // Fuente texto
        Typeface robotoR = Typeface.createFromAsset(getAssets(), "roboto/Roboto-Regular.ttf");
        Typeface robotoB = Typeface.createFromAsset(getAssets(), "roboto/Roboto-Bold.ttf");

        // Agregar fuentes
        TextView subtitle = findViewById(R.id.subtitle_login);
        TextView newAccount =  findViewById(R.id.createAccount);
        TextView loginBotton =  findViewById(R.id.button_login);


        // Asignar Fuente a Etiquetas
        subtitle.setTypeface(robotoR);
        newAccount.setTypeface(robotoR);
        loginBotton.setTypeface(robotoB);
    }

    // metodo para boton Login
    public void login (View view) {
        TextInputEditText user = findViewById(R.id.userBox);
        TextInputEditText password = findViewById(R.id.passBox);

        String name = user.getText().toString();
        String pass = password.getText().toString();

        if (name.equals("") || password.equals("")) {
            Toast.makeText(this,"Debes completar todos los campos", Toast.LENGTH_LONG).show();
        }
        if (name.length() !=0 && pass.length() != 0 ) {
            Boolean checkUserNamePass = dbController.checkUserNamePass(name,pass);
            if (checkUserNamePass) {
                sessionId = dbController.getIdUserByName(name);
                dbController.setSessionId(sessionId);
                Intent intent = new Intent(this,TaskActivity.class);
                intent.putExtra("session",sessionId);
                startActivity(intent);

            } else {
                Toast.makeText(this,"Datos no válidos", Toast.LENGTH_LONG).show();
            }
        }
    }

    // metodo Crear Cuenta
    public void createAccount (View view) {
        Intent i = new Intent(this, NewAccountActivity.class);
        startActivity(i);

    }
}