package com.example.proyectofinal_np_as.Entyti;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface GaleriaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Galeria galeria);

    @Query("SELECT * FROM galerias WHERE nombre = :nombre LIMIT 1")
    Galeria findByName(String nombre);
}

