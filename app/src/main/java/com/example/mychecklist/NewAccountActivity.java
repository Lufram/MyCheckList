package com.example.mychecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mychecklist.db.DbController;
import com.google.android.material.textfield.TextInputEditText;

public class NewAccountActivity extends AppCompatActivity {

    TextInputEditText user, pass;
    Button button_create;
    private DbController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        dbController = new DbController(this,0);



        // ocultar Action Bar
        getSupportActionBar().hide();

        // Fuente texto
        Typeface robotoR = Typeface.createFromAsset(getAssets(), "roboto/Roboto-Regular.ttf");
        Typeface robotoB = Typeface.createFromAsset(getAssets(), "roboto/Roboto-Bold.ttf");

        // Agregar fuentes
        TextView User_name = findViewById(R.id.new_user_name);
        TextView User_password =  findViewById(R.id.nuevo_password);
        TextView btnCrear =  findViewById(R.id.button_create);
        TextView btnVolver =  findViewById(R.id.button_back);


        // Asignar Fuente a Etiquetas
        User_name.setTypeface(robotoR);
        User_password.setTypeface(robotoR);
        btnCrear.setTypeface(robotoB);
        btnVolver.setTypeface(robotoR);


        user = (TextInputEditText) findViewById(R.id.new_user_name_box);
        pass = (TextInputEditText) findViewById(R.id.new_user_pass_box);
        button_create = (Button) findViewById(R.id.button_create );
    }




    // Metodo para el boton Crear nueva cuenta, validaciones
    public void btnCreate (View view) {

        SQLiteDatabase db = dbController.getWritableDatabase();

        String name = user.getText().toString();
        String password = pass.getText().toString();

        if (name.equals("") || password.equals("")) {
            Toast.makeText(this,"Debes completar todos los campos", Toast.LENGTH_LONG).show();
        }

        if (name.length() !=0 && password.length() != 0 ) {
            Boolean checkUser = dbController.checkUserNamePass(name,password);
            if (checkUser == false) {
                Boolean insert = dbController.insertData(name, password);
                if (insert == true ) {
                    Toast.makeText(this, "Registro completado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    dbController.close();
                } else {
                    Toast.makeText(this, "Registro fallido", Toast.LENGTH_LONG).show();
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