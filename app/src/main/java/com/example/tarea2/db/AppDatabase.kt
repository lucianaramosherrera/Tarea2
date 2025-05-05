package com.example.tarea2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tarea2.db.dao.IngredienteDao
import com.example.tarea2.db.dao.RecetaDao
import com.example.tarea2.db.models.Ingrediente
import com.example.tarea2.db.models.Receta
import com.example.tarea2.db.models.RecetaIngredienteCrossRef

@Database(
    entities = [Ingrediente::class, Receta::class, RecetaIngredienteCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ingredienteDao(): IngredienteDao
    abstract fun recetaDao(): RecetaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recetas_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
