package com.example.proyectofinal_np_as.Entyti;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ObraDao {
    @Insert
    void insert(Obra obra);

    @Query("SELECT * FROM obras")
    List<Obra> getAll();
}
