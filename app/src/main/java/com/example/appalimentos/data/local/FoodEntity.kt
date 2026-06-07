package com.example.appalimentos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity(

    @PrimaryKey
    val fdcId: Int,
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