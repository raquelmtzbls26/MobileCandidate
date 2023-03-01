package com.example.mobile_candidate.Interfaz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIPersonInterface {
    @GET("api/")
    Call<APIResponse> getApiresult(
            @Query("results") String results,
            @Query("gender") String gender
    );

}

