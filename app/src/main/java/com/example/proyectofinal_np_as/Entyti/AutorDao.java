package com.example.proyectofinal_np_as.Entyti;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AutorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Autor autor);

    @Query("SELECT * FROM autores WHERE nombre = :nombre LIMIT 1")
    Autor findByName(String nombre);
}
