package com.example.myapplicationlafragua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // metodo para que funcione el boton
    public void MensajeBotonBurguer(View view){

        Toast.makeText(this, "Hamburguesas", Toast.LENGTH_SHORT).show();

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), ActivityListHamburguesas.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

    }

    // metodo para que funcione el boton del bocadillo
    public void MensajeBotonBocata(View view){

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), ActivityListBocadillos.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

        Toast.makeText(this, "Bocadillos", Toast.LENGTH_SHORT).show();

    }

    // metodo para que funcione el boton del tortillas
    public void MensajeBotonTortilla(View view){

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), ActivityListTortillas.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

        Toast.makeText(this, "Tortillas", Toast.LENGTH_SHORT).show();

    }
}
