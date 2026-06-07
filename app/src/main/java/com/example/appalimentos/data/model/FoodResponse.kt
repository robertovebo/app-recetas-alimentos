package com.example.appalimentos.data.model

data class FoodResponse(
    val description: String?,
    val dataType: String?,
    val brandOwner: String?,
    val brandedFoodCategory: String?,
    val foodCategory: FoodCategory?,
    val foodNutrients: List<FoodNutrient>?
)

data class FoodCategory(
    val description: String?
)

data class FoodNutrient(
    val nutrient: Nutrient?,
    val amount: Double?
)

data class Nutrient(
    val name: String?
)