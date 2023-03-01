package com.example.mobile_candidate.Interfaz;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiFiltrarInterfaz {
    @GET("api/?gender")
    Call<APIResponse> getApiFiltrar();
}
