package com.example.tarea2.db.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Receta(
    @PrimaryKey(autoGenerate = true) val recetaId: Int = 0,
    val nombre: String,
    val preparacion: String
) : Parcelable
