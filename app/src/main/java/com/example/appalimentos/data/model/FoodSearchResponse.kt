package com.example.appalimentos.data.model

data class FoodSearchResponse(
    val foods: List<FoodSearchItem>
)

data class FoodSearchItem(
    val fdcId: Int,
    val description: String
)