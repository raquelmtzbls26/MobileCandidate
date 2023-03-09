package com.example.mobile_candidate.Modelo;

public class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private long poscode;
    private Coordinates coordinates;
    private Timezone timezone;

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street value) {
        this.street = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public long getPoscode() {
        return poscode;
    }

    public void setPoscode(long value) {
        this.poscode = value;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates value) {
        this.coordinates = value;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone value) {
        this.timezone = value;
    }

    public String getFullAddress()
    {
        return (street.getName()+" "+street.getNumber()+", "+city+"\n"+
                " "+poscode+", "+state+ ", "+country);
    }
}
