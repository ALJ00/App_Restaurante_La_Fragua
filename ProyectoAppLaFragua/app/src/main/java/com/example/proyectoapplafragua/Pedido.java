package com.example.proyectoapplafragua;

import java.util.List;

public class Pedido {

    public Pedido(List<Producto> productos) {
        this.productos = productos;
    }

    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
