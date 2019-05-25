package com.example.proyectoapplafragua;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListview extends ArrayAdapter<String> {

    String[] nombres;
    String[] descripcion;
    Integer[] imgid;
    private Activity context;

    public CustomListview(Activity context, String[] nombres, String[] descripcion, Integer[] imgid) {
        super(context, R.layout.listview_layout, nombres);

        this.context = context;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.imgid = imgid;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View r = convertView;

        ViewHolder viewHolder = null;

        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);

            viewHolder = new ViewHolder(r);

            r.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) r.getTag();

        }

        viewHolder.ivw.setImageResource(imgid[position]);

        viewHolder.tvw1.setText(nombres[position]);
        viewHolder.tvw2.setText(descripcion[position]);


        return r;
    }

    class ViewHolder {

        TextView tvw1;
        TextView tvw2;
        ImageView ivw;

        ViewHolder(View v) {

            tvw1 = (TextView) v.findViewById(R.id.tvproductname);
            tvw2 = (TextView) v.findViewById(R.id.tvdescription);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }

    }




}
