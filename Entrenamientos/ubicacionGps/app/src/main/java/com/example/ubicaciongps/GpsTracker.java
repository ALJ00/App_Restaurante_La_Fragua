package com.example.ubicaciongps;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {

    private final Context context;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;

    protected LocationManager locationManager;

    public GPSTracker (Context context) {

        this.context = context;

    }

    // funcion para obtener la localizacion
    public Location getLocation(){

        try
        {
            // accedo al la localizacion del servicio
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            // accedo al proveedor del GPS
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // accedo al proveedor de red
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){

                // si esta el GPS habilitado
                if(isGPSEnabled){

                    // si la localizacion es nula ...
                    if(location==null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,10,this);

                       // si obtengo localizacion ...
                        if(locationManager!=null){

                            // accedo a la ultima localizacion reconocida del proveedor de gps
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }

                if(location==null){

                    // si la red esta habilitada
                    if (isNetworkEnabled)
                    {
                        // compruebo la localizacion
                        if(location==null){
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,10,this);
                            if(locationManager!=null){

                                // accedo a la ultima localizacion reconocida del proveedor de red
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                        }

                    }

                }

                }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public void onLocationChanged(Location location)
    {


    }


    public void onStatusChanged (String Provider, int status, Bundle extras ){

    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

    @Override
    public void onProviderDisabled(String provider)
    {
    }



    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }




}
