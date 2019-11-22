package com.example.pac_desarrollo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //La funcionalidad de esta función es mostrar por pantalla el activity 2
    public void goActivity2(View view) {
        Intent activity2 = new Intent(this,Activity2.class);
        startActivity(activity2);
    }

    //La funcionalidad de esta función es mostrar por pantalla el activity 3
    public void goActivity3(View view) {
        Intent activity3 = new Intent(this,Activity3.class);
        startActivity(activity3);
    }
}
