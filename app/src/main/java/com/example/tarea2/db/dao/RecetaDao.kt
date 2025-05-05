package com.example.tarea2.db.dao

import androidx.room.*
import com.example.tarea2.db.models.Receta
import com.example.tarea2.db.models.RecetaConIngredientes
import com.example.tarea2.db.models.RecetaIngredienteCrossRef

@Dao
interface RecetaDao {

    @Insert
    suspend fun insertarReceta(receta: Receta): Long

    @Insert
    suspend fun insertarCrossRef(ref: RecetaIngredienteCrossRef)

    @Transaction
    @Query("SELECT * FROM Receta")
    suspend fun obtenerTodasLasRecetas(): List<RecetaConIngredientes>

    @Transaction
    @Query("""
        SELECT * FROM Receta 
        WHERE recetaId IN (
            SELECT recetaId FROM RecetaIngredienteCrossRef 
            WHERE ingredienteId IN (:ingredientesIds)
            GROUP BY recetaId
            HAVING COUNT(DISTINCT ingredienteId) = :cantidadSeleccionada
        )
    """)
    suspend fun buscarPorIngredientes(
        ingredientesIds: List<Int>,
        cantidadSeleccionada: Int
    ): List<RecetaConIngredientes>
}
