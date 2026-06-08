package com.example.appalimentos.data.model

data class RecipeInfo(
    val fdcId: Int,
    val name: String,
    val category: String?,
    val additionalDescription: String?,
    val calories: Double?,
    val protein: Double?,
    val fat: Double?,
    val carbs: Double?,
    val sugars: Double?
)