package com.example.tarea2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea2.databinding.ItemIngredienteBinding
import com.example.tarea2.db.models.Ingrediente

class IngredienteAdapter : RecyclerView.Adapter<IngredienteAdapter.ViewHolder>() {

    private val ingredientes = mutableListOf<Ingrediente>()
    private val seleccionados = mutableSetOf<Int>()

    fun setIngredientes(lista: List<Ingrediente>) {
        ingredientes.clear()
        ingredientes.addAll(lista)
        notifyDataSetChanged()
    }

    fun obtenerSeleccionados(): List<Int> = seleccionados.toList()

    inner class ViewHolder(val binding: ItemIngredienteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingrediente: Ingrediente) {
            binding.checkBox.text = ingrediente.nombre
            binding.checkBox.isChecked = seleccionados.contains(ingrediente.ingredienteId)
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    seleccionados.add(ingrediente.ingredienteId)
                } else {
                    seleccionados.remove(ingrediente.ingredienteId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = ingredientes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientes[position])
    }
}
