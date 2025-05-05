package com.example.tarea2.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea2.databinding.ActivityMainBinding
import com.example.tarea2.db.models.Ingrediente
import com.example.tarea2.ui.adapters.IngredienteAdapter
import com.example.tarea2.ui.viewmodels.IngredienteViewModel
import com.example.tarea2.ui.viewmodels.RecetaViewModel

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ingredienteViewModel: IngredienteViewModel by viewModels()
    private val recetaViewModel: RecetaViewModel by viewModels()

    private lateinit var adapter: IngredienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = IngredienteAdapter()
        binding.recyclerIngredientes.layoutManager = LinearLayoutManager(this)
        binding.recyclerIngredientes.adapter = adapter

        // Cargar todos los ingredientes
        ingredienteViewModel.obtenerTodos {
            runOnUiThread {
                adapter.setIngredientes(it)
            }
        }

        // Buscar recetas
        binding.btnBuscarRecetas.setOnClickListener {
            val ingredientesIds = adapter.obtenerSeleccionados()

            if (ingredientesIds.isEmpty()) {
                Toast.makeText(this, "Selecciona al menos un ingrediente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            recetaViewModel.buscarPorIngredientes(ingredientesIds) { recetasFiltradas ->
                runOnUiThread {
                    if (recetasFiltradas.isNotEmpty()) {
                        val intent = Intent(this, RecetasDisponiblesActivity::class.java)
                        intent.putExtra("recetasFiltradas", ArrayList(recetasFiltradas)) // Asegúrate de que sean Parcelable
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "No se encontraron recetas con esos ingredientes", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnAgregarIngrediente.setOnClickListener {
            val intent = Intent(this, AgregarIngredienteActivity::class.java)
            startActivity(intent)
        }

        binding.btnVerRecetas.setOnClickListener {
            val intent = Intent(this, RecetasDisponiblesActivity::class.java)
            startActivity(intent)
        }

        binding.btnAgregarReceta.setOnClickListener {
            val intent = Intent(this, AgregarRecetaActivity::class.java)
            startActivity(intent)
        }
    }
}

