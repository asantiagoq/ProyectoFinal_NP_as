package com.example.proyectofinal_np_as.Entyti;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "autores")
public class Autor {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;

    public Autor(String nombre) {
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}