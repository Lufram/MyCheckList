package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.db.DbController;
import com.google.android.material.textfield.TextInputEditText;

public class NewAccountActivity extends AppCompatActivity {

    TextInputEditText usuario, pass;
    Button boton_crear;
    private DbController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        // ocultar Action Bar
        getSupportActionBar().hide();

        dbController = new DbController(this);


        user = (TextInputEditText) findViewById(R.id.box_new_usuario);
        pass = (TextInputEditText) findViewById(R.id.box_new_password);
        botton_create = (Button) findViewById(R.id.botton_create );


    }


    // Metodo para el boton Crear nueva cuenta, validaciones
    public void btnCrear (View view) {

        SQLiteDatabase db = dbController.getWritableDatabase();

        String name = user.getText().toString();
        String password = pass.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this,"Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }
        if (password.length() == 0) {
            Toast.makeText(this,"Debes ingresar un password", Toast.LENGTH_LONG).show();
        }
        if (name.length() !=0 && password.length() != 0 ) {
            Boolean checkuser = dbController.checkUserNamePass(name,password);
            if (checkuser == false) {
                Boolean insert = dbController.insertData(name, password);
                if (insert == true ) {
                    Toast.makeText(this, "Registro en proceso...", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    dbController.close();
                } else {
                    Toast.makeText(this, "Registro en fallido...", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "El Usuario ya existe", Toast.LENGTH_LONG).show();
            }
        }

    }

    // Metodo para el boton Volver
    public void btnBack (View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}