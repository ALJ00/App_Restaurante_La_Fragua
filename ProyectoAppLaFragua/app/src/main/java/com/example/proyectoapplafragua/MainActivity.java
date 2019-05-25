package com.example.proyectoapplafragua;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new CarritoFragment());

    }

    private boolean loadFragment(Fragment fragment){

        if (fragment != null ){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }

        return  false;
    }

    public  boolean onNavigationItemSelected(MenuItem item){

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_carrito:
                fragment = new CarritoFragment();

                toolbar = getSupportActionBar();

                toolbar.setTitle("Carrito");

                break;

            case R.id.navigation_menus:

                fragment = new MenusFragment();

                toolbar = getSupportActionBar();

                toolbar.setTitle("Hamburguesas");

                break;

            case R.id.navigation_notifications:

                fragment = new NotificationsFragment();

                toolbar = getSupportActionBar();

                toolbar.setTitle("Realizar pedido");

                break;
        }
        return loadFragment(fragment);

    }





}
