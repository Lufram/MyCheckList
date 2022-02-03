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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbController = new DbController(this);


        // ocultar Action Bar
        getSupportActionBar().hide();

        // Fuente texto
        Typeface myFont = Typeface.createFromAsset(getAssets(), "Vuldo.ttf");

        // Agregar fuentes
        TextView subtitle = (TextView) findViewById(R.id.subtitle_login);
        TextView title = (TextView) findViewById(R.id.title_login);
        TextView newAccount = (TextView) findViewById(R.id.createAccount);
        TextView loginBotton = (TextView) findViewById(R.id.button_login);


        // Asignar Fuente a Etiquetas
        subtitle.setTypeface(myFont);
        title.setTypeface(myFont);
        newAccount.setTypeface(myFont);
        loginBotton.setTypeface(myFont);
    }

    // metodo para boton Login
    public void login (View view) {
        TextInputEditText user = (TextInputEditText) findViewById(R.id.userBox);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.passBox);

        String name = user.getText().toString();
        String pass = password.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this,"Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        if (pass.length() == 0) {
            Toast.makeText(this,"Debes ingresar un password", Toast.LENGTH_LONG).show();
        }
        if (name.length() !=0 && pass.length() != 0 ) {
            Boolean checkUserNamePass = dbController.checkUserNamePass(name,pass);
            if (checkUserNamePass) {
                Intent intent = new Intent(this,TaskActivity.class);
                startActivity(intent);
                dbController.sessionId = 0;
            } else {
                Toast.makeText(this,"Datos no válidos", Toast.LENGTH_LONG).show();
            }
        }
    }

    // metodo Crear Cuenta (necesario pasarle un View)
    public void createAccount (View view) {
        Intent i = new Intent(this, NewAccountActivity.class);
        startActivity(i);

    }
}