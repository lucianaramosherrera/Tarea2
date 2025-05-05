package com.example.tarea2.db.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecetaConIngredientes(
    @Embedded val receta: Receta,

    @Relation(
        parentColumn = "recetaId",
        entityColumn = "ingredienteId",
        associateBy = Junction(RecetaIngredienteCrossRef::class)
    )
    val ingredientes: List<Ingrediente>
) : Parcelable
