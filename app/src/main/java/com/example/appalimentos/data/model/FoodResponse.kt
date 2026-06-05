package com.example.appalimentos.data.model

data class FoodResponse(
    val description: String?,
    val foodNutrients: List<FoodNutrient>?
)

data class FoodNutrient(
    val nutrient: Nutrient?,
    val amount: Double?
)

data class Nutrient(
    val name: String?
)