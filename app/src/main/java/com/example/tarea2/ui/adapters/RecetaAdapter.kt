package com.example.tarea2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea2.databinding.ItemRecetaBinding
import com.example.tarea2.db.models.RecetaConIngredientes

class RecetaAdapter(
    private val onClick: (RecetaConIngredientes) -> Unit
) : RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {

    private var recetas = listOf<RecetaConIngredientes>()

    inner class ViewHolder(private val binding: ItemRecetaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(receta: RecetaConIngredientes) {
            binding.textNombreReceta.text = receta.receta.nombre
            val ingredientes = receta.ingredientes.joinToString(", ") { it.nombre }
            binding.textIngredientesReceta.text = "Ingredientes: $ingredientes"

            binding.root.setOnClickListener {
                onClick(receta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recetas[position])
    }

    override fun getItemCount(): Int = recetas.size

    //  actualizar la lista desde la activity
    fun setRecetas(nuevasRecetas: List<RecetaConIngredientes>) {
        recetas = nuevasRecetas
        notifyDataSetChanged()
    }
}
