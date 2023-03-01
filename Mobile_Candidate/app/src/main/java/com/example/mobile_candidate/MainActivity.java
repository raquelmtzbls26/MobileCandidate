package com.example.mobile_candidate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_candidate.Adapter.ApiAdapter;
import com.example.mobile_candidate.Interfaz.APIPersonInterface;
import com.example.mobile_candidate.Interfaz.APIResponse;
import com.example.mobile_candidate.Modelo.Result;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApiAdapter apiAdapter;
    private ChipGroup chipGroup;
    private Chip  filtro_hombre, filtro_mujer,resetfiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        recyclerView = findViewById(R.id.rv_persona);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getApiresult("");
        setUpviews();
        setUplistener();

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
                    List<Result> personas= personaList.results;

                    apiAdapter = new ApiAdapter(personas,getApplicationContext());
                    recyclerView.setAdapter(apiAdapter);
                }
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

}