package com.example.mobile_candidate.Interfaz;

import com.example.mobile_candidate.Modelo.Info;
import com.example.mobile_candidate.Modelo.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {
        @SerializedName("info")
        public Info info;
        @SerializedName("results")
        public List<Result> results;
}
