package com.example.proyectoapplafragua;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class CarritoFragment extends Fragment {

    //variables de control
    ListView mProductsList;
    PedidoProductosAdapter mProductsAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Double totalPedido = 0.0;

    // constructor
    public CarritoFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        //variable root para inflar el view con el xml layout
        View root = inflater.inflate(R.layout.fragmento_carrito, container, false);

        //  Instancias de layouts
        mProductsList = (ListView)root.findViewById(R.id.ListViewPedido);
        TextView total = (TextView)root.findViewById(R.id.addMember);
        FloatingActionButton fab = root.findViewById(R.id.fab);


        // onclick listener el envio de mensaje
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Pedido realizado correctamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // booro el pedido de Firebase
                borrarDocumentoFirebase();


            }
        });


        // Inicializar el adaptador con la fuente de datos.
        mProductsAdapter = new PedidoProductosAdapter(getActivity(), PedidoProductosRepository.getInstance().getProducts());

        // recorro la lista de productos devuelta de Productos repository
        for (int i = 0; i < PedidoProductosRepository.getInstance().getProducts().size(); i++) {

            //sumo al total de pedido
            totalPedido = totalPedido + PedidoProductosRepository.getInstance().getProducts().get(i).getPrecio();

        }

        total.setText("Total Pedido: "+ Double.toString(totalPedido));


        //Relacionando la lista con el adaptador
        mProductsList.setAdapter(mProductsAdapter);


        return root;
    }

    //funcion para borrar los pedidos de Firebase
    public  void borrarDocumentoFirebase(){


        List<Producto> productoList = PedidoProductosRepository.getInstance().getProducts();

        for (int i = 0; i < productoList.size(); i++) {

            db.collection("pedidos").document(productoList.get(i).getmId())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document", e);
                        }
                    });



        }


    }




}
