
package com.example.mobile_candidate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private final int TIEMPO = 2000;// Lapso de 2 segs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Mostrar a pantalla completa
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Ocultar la barra de acciones ActionBar
        this.getSupportActionBar().hide();


        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //lanzamos el activity principal
                Intent intent = new Intent( Splash.this, MainActivity.class);
                startActivity(intent);
                //Finalizamos el activity actual
                finish();
            }
        }, TIEMPO);
    }
}