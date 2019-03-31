package com.example.pruebaapplafragua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //metodo para el boton siguiente
    public void Siguiente(View view){

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), MainActivitySegunda.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

        //Intent siguiente = new Intent(this, MainActivitySegunda.class);
        //startActivity(siguiente);


    }
}
