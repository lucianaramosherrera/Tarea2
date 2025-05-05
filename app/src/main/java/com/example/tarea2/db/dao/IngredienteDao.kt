package com.example.tarea2.db.dao

import androidx.room.*
import com.example.tarea2.db.models.Ingrediente

@Dao
interface IngredienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(ingrediente: Ingrediente): Long

    @Query("SELECT * FROM Ingrediente ORDER BY nombre ASC")
    suspend fun obtenerTodos(): List<Ingrediente>

    @Query("SELECT * FROM Ingrediente WHERE ingredienteId IN (:ids)")
    suspend fun obtenerPorIds(ids: List<Int>): List<Ingrediente>
}
