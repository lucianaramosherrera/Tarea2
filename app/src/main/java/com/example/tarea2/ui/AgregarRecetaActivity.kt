package com.example.tarea2.ui


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea2.databinding.ActivityAgregarRecetaBinding
import com.example.tarea2.ui.adapters.IngredienteTextoAdapter
import com.example.tarea2.ui.viewmodels.IngredienteViewModel
import com.example.tarea2.ui.viewmodels.RecetaViewModel
import com.example.tarea2.db.models.Receta
import com.example.tarea2.db.models.Ingrediente

class AgregarRecetaActivity : ComponentActivity() {

    private lateinit var binding: ActivityAgregarRecetaBinding
    private val ingredienteViewModel: IngredienteViewModel by viewModels()
    private val recetaViewModel: RecetaViewModel by viewModels()

    private val ingredientesSeleccionados = mutableListOf<Ingrediente>()
    private val adapter = IngredienteTextoAdapter()
    private var todosLosIngredientes = listOf<Ingrediente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerSeleccionados.layoutManager = LinearLayoutManager(this)
        binding.recyclerSeleccionados.adapter = adapter

        // Obtener ingredientes existentes
        ingredienteViewModel.obtenerTodos {
            runOnUiThread {
                todosLosIngredientes = it
                val nombres = it.map { ing -> ing.nombre }
                val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerIngredientes.adapter = spinnerAdapter
            }
        }

        // Botón para agregar ingrediente desde
        binding.btnAgregarIngrediente.setOnClickListener {
            val pos = binding.spinnerIngredientes.selectedItemPosition
            if (pos in todosLosIngredientes.indices) {
                val seleccionado = todosLosIngredientes[pos]
                if (!ingredientesSeleccionados.contains(seleccionado)) {
                    ingredientesSeleccionados.add(seleccionado)
                    actualizarRecycler()
                }
            }
        }

        // Botón para agregar un nuevo ingrediente
        binding.btnAgregarNuevoIngrediente.setOnClickListener {
            val nombreNuevo = binding.inputNuevoIngrediente.text.toString().trim()
            if (nombreNuevo.isNotEmpty()) {
                val yaExiste = todosLosIngredientes.any { it.nombre.equals(nombreNuevo, ignoreCase = true) }
                if (!yaExiste) {
                    val nuevo = Ingrediente(nombre = nombreNuevo)
                    ingredienteViewModel.insertar(nombreNuevo)
                    // Agregarlo localmente
                    val nuevoLocal = nuevo.copy(ingredienteId = todosLosIngredientes.size + 1) // simulación
                    ingredientesSeleccionados.add(nuevoLocal)
                    actualizarRecycler()
                    Toast.makeText(this, "Ingrediente agregado", Toast.LENGTH_SHORT).show()
                    binding.inputNuevoIngrediente.text?.clear()
                } else {
                    Toast.makeText(this, "Ese ingrediente ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón para guardar la receta
        binding.btnGuardarReceta.setOnClickListener {
            val nombre = binding.inputNombre.text.toString().trim()
            val preparacion = binding.inputPreparacion.text.toString().trim()

            if (nombre.isNotEmpty() && preparacion.isNotEmpty() && ingredientesSeleccionados.isNotEmpty()) {
                val receta = Receta(nombre = nombre, preparacion = preparacion)
                val ids = ingredientesSeleccionados.map { it.ingredienteId }

                // 👉 Acá se usa el ViewModel con el Repository
                recetaViewModel.insertarRecetaConIngredientes(receta, ids)

                Toast.makeText(this, "Receta guardada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun actualizarRecycler() {
        adapter.setIngredientes(ingredientesSeleccionados.map { it.nombre })
    }
}
