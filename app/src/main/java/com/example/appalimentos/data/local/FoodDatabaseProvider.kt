package com.example.appalimentos.data.local

import android.content.Context
import androidx.room.Room

// Clase para crear la base de datos
object FoodDatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "foods_database"
            )
            .fallbackToDestructiveMigration()
            .build()

            INSTANCE = instance
            instance
        }
    }
}