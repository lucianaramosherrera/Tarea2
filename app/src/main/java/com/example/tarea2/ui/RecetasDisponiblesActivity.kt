package com.example.tarea2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea2.databinding.ActivityRecetasDisponiblesBinding
import com.example.tarea2.db.models.RecetaConIngredientes
import com.example.tarea2.ui.adapters.RecetaAdapter

class RecetasDisponiblesActivity : ComponentActivity() {

    private lateinit var binding: ActivityRecetasDisponiblesBinding
    private lateinit var adapter: RecetaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecetasDisponiblesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecetaAdapter { receta ->
            Toast.makeText(this, receta.receta.nombre, Toast.LENGTH_SHORT).show()

        }

        binding.recyclerRecetas.layoutManager = LinearLayoutManager(this)
        binding.recyclerRecetas.adapter = adapter

        // Recuperar recetas del intent
        val recetasFiltradas = intent.getParcelableArrayListExtra<RecetaConIngredientes>("recetasFiltradas")

        if (recetasFiltradas.isNullOrEmpty()) {
            Toast.makeText(this, "No se encontraron recetas con esos ingredientes", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setRecetas(recetasFiltradas)
        }
    }
}

