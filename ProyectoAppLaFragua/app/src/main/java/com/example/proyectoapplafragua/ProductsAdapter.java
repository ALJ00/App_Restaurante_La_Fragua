package com.example.proyectoapplafragua;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.List;



public class ProductsAdapter extends ArrayAdapter<Producto> {

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
                    R.layout.item_list_view,
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
        Glide.with(getContext()).load(producto.getDrawableImageID()).into(avatar);
        name.setText(producto.getmName());
        title.setText(producto.getmDescription());
        precio.setText((Double.toString(producto.getPrecio())));

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_item);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // it is check ... creo nuevo producto
                    Producto producto = getItem(position);

                    // creo objeto
                    AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper
                            (getContext(), "productos", null, 1);

                    //indico que se va a abrir la bd en modo lectura y escritura
                    SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                    ContentValues registro = new ContentValues();

                    registro.put("codigo", producto.getmId());
                    registro.put("nombre", producto.getmName());
                    registro.put("descripcion", producto.getmDescription());
                    registro.put("imagen", producto.getDrawableImageID());
                    registro.put("precio", producto.getPrecio());

                    BaseDeDatos.insert("productos", null, registro);

                    BaseDeDatos.close();

                    Toast.makeText(getContext(), "Añadido al carro el producto " + producto.getmName(), Toast.LENGTH_SHORT).show();

                } else {
                    // it is unchecked
                    Toast.makeText(getContext(), "Eliminado del carroel producto " + producto.getmName(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        return convertView;
    }
}
