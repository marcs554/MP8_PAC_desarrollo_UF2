package com.example.pac_desarrollo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class Servicio extends Service {

    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Se crea una variable integer que almacenará la posición seleccionada del spinner del activity 3
        int op = Integer.parseInt(intent.getStringExtra("spinnerPosition"));

        switch (op) {
            case 0: //inicia el audio
                //Comprueba si el audio está iniciado, de ser así lo detiene
               if (player != null && player.isPlaying()) {
                    player.stop();
                }

                player = MediaPlayer.create(this, R.raw.audio);
                player.setLooping(true);
                player.start();
                break;
            case 1: // detiene el audio
                player.stop();
                break;

            case 2: //bloquea el programa por 150000 milisegundos
                try {
                    Thread.sleep(150000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override //Para el servicio por completo
    public void onDestroy() {
        super.onDestroy();
        if(player != null) {
            player.release();
        }
        stopSelf();
    }
}
