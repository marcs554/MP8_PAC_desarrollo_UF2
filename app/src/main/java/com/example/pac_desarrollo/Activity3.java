package com.example.pac_desarrollo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    private Spinner serviceOptions;

    //Este array representa las opciones del Spinner "serviceOptions"
    private String[] spinnerOptions = {"Iniciar reproducción de música","Detener reproducción",
        "Bloquear el servicio por 15 segundos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        serviceOptions = findViewById(R.id.serviceOptions);
        serviceOptions.setEnabled(true);
        serviceOptions.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerOptions));

        Toast.makeText(this,"usted se encuentra en el Activity 3",Toast.LENGTH_LONG).show();
    }

    //Regresa al activity 1
    public void goActivity1(View view) {
        Intent activity1 = new Intent(this, MainActivity.class);
        startActivity(activity1);
    }

    //Inicia el servicio y envia la opción escogida en el spinner "serviceOptions"
    public void startService(View view) {
        int spinnerOption = serviceOptions.getSelectedItemPosition();

        Intent iniciar = new Intent(this, Servicio.class);
        iniciar.putExtra("spinnerPosition",String.valueOf(spinnerOption));
        startService(iniciar);
    }

    //Detiene el servicio
    public void stopService(View view) {
        Intent parar = new Intent(this, Servicio.class);
        stopService(parar);
    }
}
