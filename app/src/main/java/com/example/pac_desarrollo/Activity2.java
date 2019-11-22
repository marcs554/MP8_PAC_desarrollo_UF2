package com.example.pac_desarrollo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private SQLite db; //llamada al constructor de la clase SQLite
    private TextView monitor; /*La variable representará la interfaz donde se mostrará
                               *el resultado de la consulta*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        monitor = (TextView) findViewById(R.id.interfaz);
        monitor.setMovementMethod(new ScrollingMovementMethod());

        Toast.makeText(this,"usted se encuentra en el Activity 2",Toast.LENGTH_LONG).show();
    }

    //Regresa al MainActivity
    public void goActivity1(View view) {
        Intent activity1 = new Intent(this, MainActivity.class);
        startActivity(activity1);
    }

    //Abre el activity "activity_insertar_datos"
    public void insertarDatos(View view) {
        Intent intent = new Intent(this,insertar_datos.class);
        startActivity(intent);
    }

    //Consulta todos los datos de la tabla para mostrarlos por pantalla
    public void Consultar(View view) {
        db = new SQLite(this, "personas", null, 1);
        Cursor c = db.query();

        /*Cada array almacenara toda la información de cada atributo; Ej: el array nombre
        * almacenará todos los nombres del atributo nombre*/
        int i = 0;
        int[] id = new int[c.getCount()];
        String[] nombre = new String[c.getCount()];
        String[] apellidos = new String[c.getCount()];
        int[] edad = new int[c.getCount()];
        String[] email = new String[c.getCount()];

        //Esta condición comprobará si hay filas en la tabla
        if(c.getCount() > 0) {
            /*Cada ciclo es una fila que se va recorriendo, guardando en los arrays
            * y mostrando en el textView "monitor"*/
            while(c.moveToNext()) {
                id[i] = c.getInt(c.getColumnIndex("id"));
                nombre[i] = c.getString(c.getColumnIndex("nombre"));
                apellidos[i] = c.getString(c.getColumnIndex("apellidos"));
                edad[i] = c.getInt(c.getColumnIndex("edad"));
                email[i] = c.getString(c.getColumnIndex("email"));

                //
                monitor.append(id[i] + "º nombre:" + nombre[i] + " apellidos:" + apellidos[i] + " edad:" + edad[i] + " email:"
                        + email[i] + "\n");
                i++;
            }
        } else {
            monitor.setText("Tabla vacia");
        }
        c.close();
    }

    //Ordena a la base de datos crear la tabla, en caso de ue esta exista solamente se abre
    public void crearTabla(View view) {
        db = new SQLite(this, "personas", null, 1);
    }

}
