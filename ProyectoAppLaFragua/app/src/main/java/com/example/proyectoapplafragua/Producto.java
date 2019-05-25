package com.example.proyectoapplafragua;

import android.widget.ImageView;

import java.util.UUID;

public class Producto {


    private String mId;
    private String mName;
    private String mDescription;
    private int DrawableImageID;
    private double precio;

    public Producto( String mName, String mDescription, int drawableImageID, double precio) {
        this.mId = UUID.randomUUID().toString();
        this.mName = mName;
        this.mDescription = mDescription;
        DrawableImageID = drawableImageID;
        this.precio = precio;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getDrawableImageID() {
        return DrawableImageID;
    }

    public void setDrawableImageID(int drawableImageID) {
        DrawableImageID = drawableImageID;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", DrawableImageID=" + DrawableImageID +
                ", precio=" + precio +
                '}';
    }
}



