package com.example.tarea2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.tarea2.databinding.ActivityDetalleRecetaBinding
import com.example.tarea2.db.models.RecetaConIngredientes

class DetalleRecetaActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetalleRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receta = intent.getSerializableExtra("receta") as? RecetaConIngredientes

        receta?.let {
            binding.textNombre.text = it.receta.nombre
            binding.textPreparacion.text = it.receta.preparacion
            val ingredientes = it.ingredientes.joinToString(separator = "\n") { ing -> "- ${ing.nombre}" }
            binding.textIngredientes.text = ingredientes
        }
    }
}


