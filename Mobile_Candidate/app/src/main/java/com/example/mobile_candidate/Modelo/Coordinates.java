package com.example.mobile_candidate.Modelo;

public class Coordinates {
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String value) {
        this.latitude = value;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String value) {
        this.longitude = value;
    }

    public String getFullCoordenadas()
    {
        return (getLongitude() + " , " + getLatitude());
    }
}
