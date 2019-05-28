package com.example.proyectoapplafragua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.support.constraint.Constraints.TAG;


public class ProductsAdapter extends ArrayAdapter<Producto> {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProductsAdapter(Context context, List<Producto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.listview_layout,
                    parent,
                    false);

        }

        // Producto actual.
        final Producto producto = getItem(position);

        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageView);
        TextView name = (TextView) convertView.findViewById(R.id.tvproductname);
        TextView title = (TextView) convertView.findViewById(R.id.tvdescription);
        TextView precio = (TextView) convertView.findViewById(R.id.tvprecioProducto);

        // Setup.
        try {
            Glide.with(getContext()).load(producto.getDrawableImageID()).into(avatar);
            name.setText(producto.getmName());
            title.setText(producto.getmDescription());
            precio.setText((Double.toString(producto.getPrecio())));

        } catch (Exception e) {
            e.printStackTrace();
        }


        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_item);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // it is check ... creo nuevo producto
                    Producto producto = getItem(position);
                    Date fecha = new Date();

                    // Create a new user with a first and last name
                    Map<String, Object> productos = new HashMap<>();

                    String img = String.valueOf(producto.getDrawableImageID());
                    String price = String.valueOf(producto.getPrecio());

                    productos.put("id", producto.getmId());
                    productos.put("nombre", producto.getmName());
                    productos.put("descripcion", producto.getmDescription());
                    productos.put("imagen",img );
                    productos.put("precio", price);
                    productos.put("fecha", fecha);

                    // inserto prpducto en Firebase
                    insertarProductoFirebase(producto, productos);

                    Toast.makeText(getContext(), "Añadido al carro el producto " + producto.getmName(), Toast.LENGTH_SHORT).show();

                } else {

                    borrarDocumentoFirebase(producto.getmId());
                    // it is unchecked
                    Toast.makeText(getContext(), "Eliminado del carro el producto " + producto.getmName(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        return convertView;
    }

    public  void borrarDocumentoFirebase(String id){

        db.collection("pedidos").document(id)
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

    public void insertarProductoFirebase(Producto producto, Map<String, Object> objectMap ){

        // Add a new document with a generated ID
        db.collection("pedidos").document(producto.getmId())
                .set(objectMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }








}
