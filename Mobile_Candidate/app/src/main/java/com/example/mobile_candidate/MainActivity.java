package com.example.mobile_candidate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_candidate.Adapter.ApiAdapter;
import com.example.mobile_candidate.Interfaz.APIPersonInterface;
import com.example.mobile_candidate.Interfaz.APIResponse;
import com.example.mobile_candidate.Modelo.Result;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.OcultarTecladoAdaptador;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private ApiAdapter apiAdapter;
    private ChipGroup chipGroup;
    private Chip  filtro_hombre, filtro_mujer,resetfiltro;
    SearchView txtBuscar;


    private RelativeLayout relativeLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        txtBuscar = findViewById(R.id.txtBuscar);
        txtBuscar.setOnQueryTextListener(this);
        relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(new OcultarTecladoAdaptador(this ));

        recyclerView = findViewById(R.id.rv_persona);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getApiresult("");
        setUpviews();
        setUplistener();
       // setDayNight();
    }

    private void getApiresult(String gender){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIPersonInterface apipersona = retrofit.create(APIPersonInterface.class);

        Call<APIResponse> call = apipersona.getApiresult("50", gender);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    APIResponse personaList = response.body();
                    ArrayList<Result> personas= personaList.results;

                    apiAdapter = new ApiAdapter(personas,getApplicationContext());
                    recyclerView.setAdapter(apiAdapter);
                }

            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No se pudieron cargar los datos, corrobore su conexion", Toast.LENGTH_SHORT).show();
            }

        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    //
    private void setUpviews(){
        resetfiltro = findViewById(R.id.resetfiltro);
        filtro_mujer = findViewById(R.id.filtromujer);
        filtro_hombre = findViewById(R.id.filtrohombre);
    }

    private void setUplistener(){

        resetfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApiresult("");
            }
        });

        filtro_hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApiresult("male");
            }
        });
        filtro_mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApiresult("female");
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        apiAdapter.filtrado(s);
        return false;
    }



}