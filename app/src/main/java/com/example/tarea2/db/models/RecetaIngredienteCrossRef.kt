package com.example.tarea2.db.models

import androidx.room.Entity

@Entity(primaryKeys = ["recetaId", "ingredienteId"])
data class RecetaIngredienteCrossRef(
    val recetaId: Int,
    val ingredienteId: Int
)
