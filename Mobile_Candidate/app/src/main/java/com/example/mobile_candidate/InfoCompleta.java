package com.example.mobile_candidate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.mobile_candidate.Modelo.Coordinates;
import com.example.mobile_candidate.Modelo.Location;
import com.facebook.shimmer.ShimmerFrameLayout;

public class InfoCompleta extends AppCompatActivity {
    private TextView txtvTelefono, txtvDireccion, tx_Name1,txtvVCell;
    private ImageView imgvPersona;
    private ScaleGestureDetector detector;
    private float xBegin = 0;
    private float yBegin = 0;
    ShimmerFrameLayout shimmerinfo;
    private CardView cardView, cardViewCarga;
    private EditText edtLatitud, edtLongitud;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_infocompleta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgvPersona = findViewById(R.id.imgvPersona);
        xBegin = imgvPersona.getScaleX();
        yBegin = imgvPersona.getScaleY();

        detector  = new ScaleGestureDetector(this, new ScaleListener(imgvPersona));

        tx_Name1 = findViewById(R.id.tx_Name1);
        txtvTelefono = findViewById(R.id.txtvTelefono);
        txtvVCell = findViewById(R.id.txtvVCell);
        txtvDireccion = findViewById(R.id.txtvDireccion);

        shimmerinfo = findViewById(R.id.shimmerinfo);
        shimmerinfo.startShimmer();


        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phone = intent.getExtras().getString("phone");
        String cell = intent.getExtras().getString("cell");
        String location = intent.getExtras().getString("location");
        String picture = intent.getExtras().getString("picture");
        String location1 = intent.getExtras().getString("location1");

        tx_Name1.setText("Nombre: " + name);
        txtvTelefono.setText("Telefono: "+ phone);
        txtvVCell.setText("Celular: "+ cell);
        txtvDireccion.setText("Direccion: " + location);
        Glide.with(getApplicationContext()).load(picture).into(imgvPersona);

        txtvTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+" + phone));
                startActivity(intent1);
            }
        });

        txtvVCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+" + cell));
                startActivity(intent1);
            }
        });

        edtLatitud  = findViewById(R.id.edtLatitud);
        edtLongitud = findViewById(R.id.edtLongitud);

        txtvDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Location locationCord = new Location();
                Coordinates longitud = new Coordinates();
                String location1  = "25.520000610259583";
                String latitud = "-103.40321078220909";

                String uri = "geo:" + latitud + ","location1;*/
                String latitud = edtLatitud.getText().toString();
                String longitud = edtLongitud.getText().toString();
                String uri = "geo:"+ latitud + "," + longitud;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });


        cardView = findViewById(R.id.tarjeta);
        cardViewCarga = findViewById(R.id.cargatarjeta);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cardViewCarga.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
