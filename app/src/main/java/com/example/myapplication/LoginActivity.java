package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.DbController;
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
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "Vuldo.ttf");

        // Agregar fuentes
        TextView subtitulo = (TextView) findViewById(R.id.subtitulo_login);
        TextView titulo = (TextView) findViewById(R.id.titulo_login);
        TextView cuentaNueva = findViewById(R.id.crear_cuenta);
        TextView botonLogin = (TextView) findViewById(R.id.boton_login);


        // Asignar Fuente a Etiquetas
        subtitulo.setTypeface(miFuente);
        titulo.setTypeface(miFuente);
        cuentaNueva.setTypeface(miFuente);
        botonLogin.setTypeface(miFuente);
    }

    // metodo para boton Login
    public void login (View view) {
        TextInputEditText user = (TextInputEditText) findViewById(R.id.cajaUser);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.cajaPass);

        String name = user.getText().toString();
        String pass = password.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this,"Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        if (pass.length() == 0) {
            Toast.makeText(this,"Debes ingresar un password", Toast.LENGTH_LONG).show();
        }
        if (name.length() !=0 && pass.length() != 0 ) {
            Boolean checkUserNamePass = dbController.checkUserNamePass(name,password);
            if (checkUserNamePass) {
                Intent intent = new Intent(this,TaskActivity.class);
                startActivity(intent);
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