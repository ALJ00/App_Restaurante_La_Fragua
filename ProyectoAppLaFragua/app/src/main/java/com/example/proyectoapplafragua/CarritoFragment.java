package com.example.proyectoapplafragua;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CarritoFragment extends Fragment {

    ListView mProductsList;
    PedidoProductosAdapter mProductsAdapter;

    public CarritoFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View root = inflater.inflate(R.layout.fragmento_carrito, container, false);

        // Instancia del ListView.
        mProductsList = (ListView)root.findViewById(R.id.ListViewPedido);


        // Inicializar el adaptador con la fuente de datos.
        mProductsAdapter = new PedidoProductosAdapter(getActivity(), ProductsRepository.getInstance().getProducts());


        //Relacionando la lista con el adaptador
        mProductsList.setAdapter(mProductsAdapter);


        return root;
    }
}
