package com.example.proyectofinal_np_as.Entyti;

public class Obra {
    private String name;
    private int imagen;
    private String descripcion;
    private String audioUrl;

    public Obra(String name){
        this.name = name;
    }
    public Obra(String name, int imagen){
        this.name = name;
        this.imagen = imagen;
    }
    public Obra(String name, int imagen, String descripcion, String audioUrl) {
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
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
