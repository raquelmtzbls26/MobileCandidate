package com.example.mobile_candidate.Modelo;

import java.util.List;

public class Api {
    private List<Persona> results;
    private Info info;

    public List<Persona> getResults(){ return results; }
    public void setResults(List<Persona> value){this.results = value; }

    public Info getInfo(){ return info; }

    public void setInfo(Info info) { this.info = info; }
}
