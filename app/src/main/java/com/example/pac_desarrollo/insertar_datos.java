package com.example.pac_desarrollo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertar_datos extends AppCompatActivity {

    protected EditText nombre;
    protected EditText apellidos;
    protected EditText edad;
    protected EditText email;
    private SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_datos);

        nombre = (EditText) findViewById(R.id.et_nombre);
        apellidos = (EditText) findViewById(R.id.et_apellidos);
        edad = (EditText) findViewById(R.id.et_edad);
        email = (EditText) findViewById(R.id.et_email);
    }

    //Regresa al activity 2
    public void goActivity2(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    //Inserta los datos introducidos en los editText a la tabla
    public void insert(View view) {
        db = new SQLite(this, "personas", null, 1);

        //El try catch se encargará de avisar si el usuario se dejo un editText sin cubrir
        //De ser así aparecerá un toast informando al usuario de que no debe dejar campos en blanco
        try {
            String name = nombre.getText().toString();
            String lastName = apellidos.getText().toString();
            int age = Integer.parseInt(edad.getText().toString());
            String mail = email.getText().toString();

            db.insert(name, lastName, age, mail, this);
        } catch (Exception e) {
            Toast.makeText(this, "Error: No se permiten campos vacios", Toast.LENGTH_LONG).show();
        }

    }

}
