package com.example.mobile_candidate.Modelo;

public class Name {
    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String value) {
        this.first = value;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String value) {
        this.last = value;
    }

    public String getFullName()
    {
        return first+" "+last;
    }
}
