package com.example.proyectofinal_np_as.Entyti;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "obras")
public class Obra {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int imagen;
    private String descripcion;
    private String audioUrl;
    private int autorId;
    private int galeriaId;

    // Constructor para Room
    public Obra(String name, int imagen, String descripcion, String audioUrl, int autorId, int galeriaId) {
        this.name = name;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.audioUrl = audioUrl;
        this.autorId = autorId;
        this.galeriaId = galeriaId;
    }

    // Constructor adicional para ListaObrasFragment
    @Ignore
    public Obra(String name, int imagen) {
        this.name = name;
        this.imagen = imagen;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getGaleriaId() {
        return galeriaId;
    }

    public void setGaleriaId(int galeriaId) {
        this.galeriaId = galeriaId;
    }
}
