package com.example.reciclable;

public class Contacto {
    private String nombre;
    private String apeMat;
    private String apePat;
    private static int nContacto = 0;

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    private boolean favorito;

    public Contacto(String nombre, String email, String cel){
        this.apeMat = email;
        this.apePat = cel;
        this.nombre = nombre;
        this.favorito = false;
    }

    public Contacto(){
        nContacto++;
        this.apeMat = "apePat "+nContacto;
        this.apePat =  "apeMat "+nContacto;
        this.nombre =  "nombre "+nContacto;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }
}
