package com.example.proyectofinal_np_as.Fragments;

import android.graphics.Bitmap;

public class Obra {
    private String name;
    private Bitmap imagen;
    private String descripcion;
    private String audioUrl;

    public Obra(String name, String descripcion){
        this.name = name;
        this.descripcion = descripcion;
    }
    public Obra(String name, Bitmap imagen, String descripcion, String audioUrl) {
        this.name = name;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.audioUrl = audioUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
