package com.example.appalimentos.data.local

import androidx.room.Entity

@Entity(tableName = "recipes", primaryKeys = ["fdcId", "userEmail"])
data class RecipeEntity(
    val fdcId: Int,
    val userEmail: String,
    val name: String,
    val dataType: String?,
    val category: String?,
    val calories: Double?,
    val protein: Double?,
    val fat: Double?,
    val carbs: Double?,
    val sugars: Double?,
    val additionalDescription: String?
)