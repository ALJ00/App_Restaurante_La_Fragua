package com.example.proyectoapplafragua;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class PedidoProductosAdapter extends ArrayAdapter<Producto> {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public PedidoProductosAdapter(Context context, List<Producto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Double totalPedido = 0.0;

        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_list_view,
                    parent,
                    false);


        }

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageView2);
        TextView name = (TextView) convertView.findViewById(R.id.tvproductnamePedido);
        TextView title = (TextView) convertView.findViewById(R.id.tvdescriptionPedido);
        TextView precio = (TextView) convertView.findViewById(R.id.tvprecioProductoPedido);


        // Producto actual.
        final Producto producto = getItem(position);


        // Setup.
        Glide.with(getContext()).load(producto.getDrawableImageID()).into(avatar);
        name.setText(producto.getmName());
        title.setText(producto.getmDescription());
        precio.setText((Double.toString(producto.getPrecio())));


        Button button = (Button) convertView.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Producto producto = getItem(position);

                borrarDocumentoFirebase(producto.getmId());

                Toast.makeText(getContext(), "Eliminado del carro el producto " + producto.getmName(), Toast.LENGTH_SHORT).show();

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


}