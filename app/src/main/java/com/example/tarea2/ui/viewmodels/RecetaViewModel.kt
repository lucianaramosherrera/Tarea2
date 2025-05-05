package com.example.tarea2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea2.db.models.Receta
import com.example.tarea2.db.models.RecetaConIngredientes
import com.example.tarea2.ui.repositorio.RecetaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    fun insertarRecetaConIngredientes(receta: Receta, ingredientesIds: List<Int>) {
        RecetaRepository.insertarRecetaConIngredientes(context, receta, ingredientesIds)
    }

    fun obtenerTodas(callback: (List<RecetaConIngredientes>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = RecetaRepository.obtenerTodas(context)
            callback(lista)
        }
    }

    fun buscarPorIngredientes(ingredientesIds: List<Int>, callback: (List<RecetaConIngredientes>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultados = RecetaRepository.obtenerTodas(context).filter { recetaConIng ->
                val ingredientesReceta = recetaConIng.ingredientes.map { it.ingredienteId }

                ingredientesReceta.size == ingredientesIds.size && ingredientesIds.all { it in ingredientesReceta }
            }
            callback(resultados)
        }
    }

}

