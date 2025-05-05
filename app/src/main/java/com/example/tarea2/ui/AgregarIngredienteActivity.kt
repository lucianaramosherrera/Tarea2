package com.example.tarea2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tarea2.databinding.ActivityAgregarIngredienteBinding
import com.example.tarea2.ui.viewmodels.IngredienteViewModel
import com.example.tarea2.ui.viewmodels.IngredienteViewModelFactory

class AgregarIngredienteActivity : ComponentActivity() {

    private lateinit var binding: ActivityAgregarIngredienteBinding
    private lateinit var ingredienteViewModel: IngredienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarIngredienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = IngredienteViewModelFactory(application)
        ingredienteViewModel = ViewModelProvider(this, factory).get(IngredienteViewModel::class.java)

        binding.btnGuardarIngrediente.setOnClickListener {
            val nombre = binding.inputNombreIngrediente.text.toString().trim()
            if (nombre.isNotEmpty()) {
                ingredienteViewModel.insertar(nombre)
                Toast.makeText(this, "Ingrediente guardado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
