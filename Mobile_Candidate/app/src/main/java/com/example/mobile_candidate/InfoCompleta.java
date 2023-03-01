package com.example.mobile_candidate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class InfoCompleta extends AppCompatActivity {
    private TextView txtvTelefono, txtvDireccion, tx_Name1;
    private ImageView imgvPersona;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_infocompleta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgvPersona = findViewById(R.id.imgvPersona);
        tx_Name1 = findViewById(R.id.tx_Name1);
        txtvTelefono = findViewById(R.id.txtvTelefono);
        txtvDireccion = findViewById(R.id.txtvDireccion);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phone = intent.getExtras().getString("phone");
        String location = intent.getExtras().getString("location");
        String picture = intent.getExtras().getString("picture");

        tx_Name1.setText(name);
        txtvTelefono.setText(phone);
        txtvDireccion.setText(location);
        Glide.with(getApplicationContext()).load(picture).into(imgvPersona);

    }

}
