package com.example.tarea2.ui.repositorio

import android.content.Context
import com.example.tarea2.db.AppDatabase
import com.example.tarea2.db.models.Receta
import com.example.tarea2.db.models.RecetaConIngredientes
import com.example.tarea2.db.models.RecetaIngredienteCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object RecetaRepository {
    fun insertarRecetaConIngredientes(context: Context, receta: Receta, ingredientesIds: List<Int>) {
        val dao = AppDatabase.getInstance(context).recetaDao()
        CoroutineScope(Dispatchers.IO).launch {
            val recetaId = dao.insertarReceta(receta)
            ingredientesIds.forEach { id ->
                dao.insertarCrossRef(RecetaIngredienteCrossRef(recetaId.toInt(), id))
            }
        }
    }

    suspend fun obtenerTodas(context: Context): List<RecetaConIngredientes> {
        return AppDatabase.getInstance(context).recetaDao().obtenerTodasLasRecetas()
    }
}
