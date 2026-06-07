package com.example.appalimentos.data.model

data class NutritionInfo(
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