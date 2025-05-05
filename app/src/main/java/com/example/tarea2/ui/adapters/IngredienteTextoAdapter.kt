package com.example.tarea2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea2.databinding.ItemIngredienteTextoBinding

class IngredienteTextoAdapter : RecyclerView.Adapter<IngredienteTextoAdapter.ViewHolder>() {

    private val ingredientes = mutableListOf<String>()

    fun setIngredientes(lista: List<String>) {
        ingredientes.clear()
        ingredientes.addAll(lista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemIngredienteTextoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nombre: String) {
            binding.textIngrediente.text = nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredienteTextoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = ingredientes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientes[position])
    }
}
