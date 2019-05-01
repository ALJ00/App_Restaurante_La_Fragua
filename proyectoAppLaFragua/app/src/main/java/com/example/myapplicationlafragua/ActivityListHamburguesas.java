package com.example.myapplicationlafragua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ActivityListHamburguesas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hamburguesas);
    }

    // metodo para que funcione el boton
    public void MensajeBotonRegresar(View view){

        Toast.makeText(this, "Hamburguesas", Toast.LENGTH_SHORT).show();

        // a la vez que hago un start de activity la voy eliminando de la pila de activitys
        startActivity(new Intent(getBaseContext(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();

    }
}
