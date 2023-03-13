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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.mobile_candidate.Modelo.Coordinates;
import com.example.mobile_candidate.Modelo.Location;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoCompleta extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    private TextView txtvTelefono, txtvDireccion, tx_Name1,txtvVCell, txtLatitud, txtLongitud;
    private ImageView imgvPersona;
    private ScaleGestureDetector detector;
    private float xBegin = 0;
    private float yBegin = 0;
    ShimmerFrameLayout shimmerinfo;
    private CardView cardView, cardViewCarga;

    GoogleMap mMapa;

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
        DatosObj datosObj = intent.getParcelableExtra("datosObj");

        tx_Name1.setText(datosObj.getName()); //"Nombre: " + name);
        txtvTelefono.setText(datosObj.getPhone());//"Telefono: "+ phone);
        txtvVCell.setText(datosObj.getCell());//"Celular: "+ cell);
        txtvDireccion.setText(datosObj.getLocation()); // location);
        Glide.with(getApplicationContext()).load(datosObj.getPicture()).into(imgvPersona);//picture).into(imgvPersona);

        txtvTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+" + datosObj.getPhone()));
                startActivity(intent1);
            }
        });

        txtvVCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+" + datosObj.getCell()));
                startActivity(intent1);
            }
        });

        txtLatitud  = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        txtLatitud.setText(datosObj.getLatitud());//latitud);
        txtLongitud.setText(datosObj.getLongitud());//longitud);
        /*txtvDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitud = txtLatitud.getText().toString();
                String longitud = txtLongitud.getText().toString();
                String uri = "geo:"+ latitud + " , " + longitud;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });*/

        cardView = findViewById(R.id.tarjeta);
        cardViewCarga = findViewById(R.id.cargatarjeta);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cardViewCarga.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
            }
        }, 1500);

        //inicializando el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));

    }

    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMapa = googleMap;
        txtvDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                DatosObj datosObj = intent.getParcelableExtra("datosObj");

                txtLatitud.setText(datosObj.getLatitud());//latitud);
                txtLongitud.setText(datosObj.getLongitud());//longitud);

                String latitud = txtLatitud.getText().toString();
                String longitud = txtLongitud.getText().toString();
                String uri = "geo:"+ latitud + " , " + longitud;

                LatLng mexico = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));


                mMapa.addMarker(new MarkerOptions().position(mexico).title("Mexico"));
                mMapa.moveCamera(CameraUpdateFactory.newLatLng(mexico));

            }
        });
    }

    //Para dar un solo click se genere
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }

    //Dejar precionado
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText(""+ latLng.longitude);
    }
}
