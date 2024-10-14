package com.example.practica10_listviewlamda;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String description;
    private float precio;
    private String imagen;


    public Producto(String nombre, String description, float precio) {
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.imagen = null;

    }
    public Producto(String nombre, String description, float precio, String imagen) {
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.imagen= imagen;

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
