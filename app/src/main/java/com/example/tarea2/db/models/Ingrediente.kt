package com.example.tarea2.db.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Ingrediente(
    @PrimaryKey(autoGenerate = true) val ingredienteId: Int = 0,
    val nombre: String
) : Parcelable
