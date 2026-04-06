package org.example.entities;

public class Producto {
    private int idProducto;
    private String nombre;
    private Float valor;


    public Producto() {
    }

    public Producto(int idProducto, String nombre, Float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }


}
