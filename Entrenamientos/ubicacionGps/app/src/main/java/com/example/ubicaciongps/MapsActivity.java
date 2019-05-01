package com.example.ubicaciongps;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

// clase MapsActivity que se encarga de poner el mapa con las coordendas obtenidas de la clase GPSTRacker
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Variables de acceso
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;

    // variables de control de la latitud y longitud
    double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // instancio un objeto GPSTracker
        gpsTracker = new GPSTracker(getApplicationContext());

        // accedo al metodo get del objeto GPSTracker
        mLocation = gpsTracker.getLocation();

        // doy valor a las variables de control inicialmente creadas
        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();



        // verifico actualizacion de servicios de GooglePlay
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);

            dialog.show();
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near latitude's and longitud´s variables.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //funcion que se encarga de marcar las coordenadas en el mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //variable de acceso a GoogelMap
        mMap = googleMap;

        // selecciono tipo de mapa satelite, hibrida, terrain...
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // habilito utilidades de google maps
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);


        // Inserto longitud y latitud para luego marcar en el mapa
        LatLng posicion = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(posicion).title("Hola estoy aquí").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        // zoom
        float zoom = 15;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, zoom));


    }
}