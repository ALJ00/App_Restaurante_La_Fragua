package com.example.proyectoapplafragua;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MenusFragment extends Fragment {


    ListView mProductsList;
    ProductsAdapter mProductsAdapter;


    public MenusFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View root = inflater.inflate(R.layout.fragment_menus, container, false);

        // Instancia del ListView.
        mProductsList = (ListView)root.findViewById(R.id.ListView);


        // Inicializar el adaptador con la fuente de datos.
        mProductsAdapter = new ProductsAdapter(getActivity(), ProductsRepository.getInstance().getProducts());


        //Relacionando la lista con el adaptador
        mProductsList.setAdapter(mProductsAdapter);


        return root;
    }





}



