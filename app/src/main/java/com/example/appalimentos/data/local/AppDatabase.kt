package com.example.appalimentos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// bdd local 2 tablas
@Database(
    entities = [
        FoodEntity::class,
        RecipeEntity::class
    ],
    version = 3,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    abstract fun recipeDao(): RecipeDao
}