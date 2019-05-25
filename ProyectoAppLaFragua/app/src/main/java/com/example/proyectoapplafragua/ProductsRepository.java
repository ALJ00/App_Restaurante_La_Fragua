package com.example.proyectoapplafragua;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ProductsRepository {

    private static ProductsRepository repository = new ProductsRepository();
    private HashMap<String, Producto> products = new HashMap<>();



    public static ProductsRepository getInstance() {
        return repository;
    }


    private ProductsRepository() {
        saveProduct(new Producto("hamburguesa", "Especial de carne y huevo",R.drawable.burguer2,10));
        saveProduct(new Producto("Hambuguesa Huevo", "Hamburguesa especial compuesta de carne de vacuno, tomate, pepinillo y " +
                "queso", R.drawable.ic_home_black_24dp,10));
        saveProduct(new Producto("Hamburguesa especial", "Hamburguesa supreme", R.drawable.tortilla,10));

    }

    private void saveProduct(Producto producto) {
        products.put(producto.getmId(), producto);
    }

    public List<Producto> getProducts() {
        return new ArrayList<>(products.values());
    }



}
