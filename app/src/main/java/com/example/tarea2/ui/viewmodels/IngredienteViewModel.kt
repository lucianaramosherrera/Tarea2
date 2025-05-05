package com.example.tarea2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea2.db.AppDatabase
import com.example.tarea2.db.models.Ingrediente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel

class IngredienteViewModel(application: Application) : AndroidViewModel(application) {

    private val ingredienteDao = AppDatabase.getInstance(application).ingredienteDao()

    fun insertar(nombre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ingrediente = Ingrediente(nombre = nombre)
            ingredienteDao.insertar(ingrediente)
        }
    }

    fun obtenerTodos(callback: (List<Ingrediente>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = ingredienteDao.obtenerTodos()
            callback(lista)
        }
    }
}
