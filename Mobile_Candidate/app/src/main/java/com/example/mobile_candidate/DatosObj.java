package com.example.mobile_candidate;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosObj implements Parcelable {
     private String name , phone, cell, location, picture, latitud, longitud;

    public DatosObj() {
    }

    public DatosObj(String name, String phone, String cell, String location, String picture, String latitud, String longitud) {
        this.name = name;
        this.phone = phone;
        this.cell = cell;
        this.location = location;
        this.picture = picture;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
        dest.writeString(this.location);
        dest.writeString(this.picture);
        dest.writeString(this.latitud);
        dest.writeString(this.longitud);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.phone = source.readString();
        this.cell = source.readString();
        this.location = source.readString();
        this.picture = source.readString();
        this.latitud = source.readString();
        this.longitud = source.readString();
    }

    protected DatosObj(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.cell = in.readString();
        this.location = in.readString();
        this.picture = in.readString();
        this.latitud = in.readString();
        this.longitud = in.readString();
    }

    public static final Parcelable.Creator<DatosObj> CREATOR = new Parcelable.Creator<DatosObj>() {
        @Override
        public DatosObj createFromParcel(Parcel source) {
            return new DatosObj(source);
        }

        @Override
        public DatosObj[] newArray(int size) {
            return new DatosObj[size];
        }
    };
}
