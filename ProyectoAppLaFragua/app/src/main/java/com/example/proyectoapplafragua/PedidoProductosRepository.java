package com.example.proyectoapplafragua;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class PedidoProductosRepository {

    private static PedidoProductosRepository repository = new PedidoProductosRepository();
    private HashMap<String, Producto> products = new HashMap<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static PedidoProductosRepository getInstance() {
        return repository;
    }

    private PedidoProductosRepository() {

        db.collection("pedidos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Map<String, Object> datos = document.getData();

                                String id = (String) datos.get("id");
                                String name = (String) datos.get("nombre");
                                String desc = (String) datos.get("descripcion");
                                String img = (String) datos.get("imagen");
                                String precio = (String) datos.get("precio");

                                Integer i = Integer.parseInt(img);
                                Double d = Double.parseDouble(precio);

                                Log.d(TAG, name);

                                Producto nuevoProducto = new Producto();

                                nuevoProducto.setmId(id);
                                nuevoProducto.setmName(name);
                                nuevoProducto.setmDescription(desc);
                                nuevoProducto.setDrawableImageID(i);
                                nuevoProducto.setPrecio(d);


                                saveProduct(nuevoProducto);


                              Log.d(TAG, document.getId() + " => " + document.getData());

                            }

                        } else {

                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });




    }

    private void saveProduct(Producto producto) {
        products.put(producto.getmId(), producto);

    }

    public List<Producto> getProducts() {
        return new ArrayList<>(products.values());
    }














}
