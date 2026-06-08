package com.example.appalimentos.data.local

import androidx.room.Entity

@Entity(tableName = "foods", primaryKeys = ["fdcId", "userEmail"])
data class FoodEntity(
    val fdcId: Int,
    val userEmail: String,
    val name: String,
    val dataType: String?,
    val category: String?,
    val brand: String?,
    val calories: Double?,
    val protein: Double?,
    val fat: Double?,
    val carbs: Double?,
    val sugars: Double?
)