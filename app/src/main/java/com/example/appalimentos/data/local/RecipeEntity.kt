package com.example.appalimentos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(

    @PrimaryKey
    val fdcId: Int,
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