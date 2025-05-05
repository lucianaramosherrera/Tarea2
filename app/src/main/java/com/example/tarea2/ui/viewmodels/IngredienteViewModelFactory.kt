package com.example.tarea2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IngredienteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredienteViewModel::class.java)) {
            return IngredienteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}