package com.example.pruebaapplafragua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivitySegunda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_segunda);
    }

    //Metodo para le boton anterior
    public void Anterior(View view){

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

        //Intent anterior = new Intent(this, MainActivity.class);
        //startActivity(anterior);
    }
}
