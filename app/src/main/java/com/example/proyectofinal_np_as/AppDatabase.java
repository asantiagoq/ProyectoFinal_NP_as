package com.example.proyectofinal_np_as;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proyectofinal_np_as.Entyti.Autor;
import com.example.proyectofinal_np_as.Entyti.AutorDao;
import com.example.proyectofinal_np_as.Entyti.Galeria;
import com.example.proyectofinal_np_as.Entyti.GaleriaDao;
import com.example.proyectofinal_np_as.Entyti.Obra;
import com.example.proyectofinal_np_as.Entyti.ObraDao;

@Database(entities = {Autor.class, Obra.class, Galeria.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract ObraDao obraDao();
    public abstract GaleriaDao galeriaDao();


}

