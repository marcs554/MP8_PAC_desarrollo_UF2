package com.example.pac_desarrollo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    public SQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override //Crea la tabla usuarios con los atributos: id, nombre, apellidos, edad, email
    public void onCreate(SQLiteDatabase db) {
        String tablaUsuarios = "CREATE TABLE usuarios" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR(20)," +
                "apellidos VARCHAR(30)," +
                "edad INTEGER," +
                "email VARCHAR(20))";
        db.execSQL(tablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*Se encarga de insertar los datos enviados desde la funcion "insertar_datos.insert()"
    * en la tabla de la base de datos y envia un toast informando de que la acción se realizo correctamente*/
    public void insert(String nombre, String apellidos, int edad, String email, Context context) {
        SQLiteDatabase insertar = null;
        try {
            insertar = getWritableDatabase();

            insertar.execSQL("INSERT INTO usuarios (nombre,apellidos,edad,email)" +
                    "VALUES (\"" + nombre + "\", \""  + apellidos + "\", " + edad + ",\"" + email + "\")");
            Toast.makeText(context,"operacion realizada correctamente", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context,"Error, no se ha podido insertar los datos \nMotivo: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            insertar.close();
        }
    }

    //Cuando el metodo es llamado se retorna el contenido de la tabla usuarios como un Cursor
    public Cursor query() {
        SQLiteDatabase consultar = getReadableDatabase();
        return consultar.rawQuery("SELECT * FROM usuarios",null);
    }
}
