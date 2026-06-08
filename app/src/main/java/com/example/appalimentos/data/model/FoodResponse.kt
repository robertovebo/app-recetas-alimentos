package com.example.appalimentos.data.model

data class FoodResponse(
    val description: String?,
    val dataType: String?,

    // Branded
    val brandOwner: String?,
    val brandedFoodCategory: String?,

    // Foundation / SR Legacy
    val foodCategory: FoodCategory?,

    // Survey (FNDDS)
    val wweiaFoodCategory: WweiaFoodCategory?,
    val foodAttributes: List<FoodAttribute>?,

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

// recetas
data class WweiaFoodCategory(

    val wweiaFoodCategoryDescription: String?
)

data class FoodAttribute(
    val value: String?,
    val foodAttributeType: FoodAttributeType?
)

data class FoodAttributeType(
    val name: String?
)
