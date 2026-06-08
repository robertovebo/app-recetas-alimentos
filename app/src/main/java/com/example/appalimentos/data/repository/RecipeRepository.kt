package com.example.appalimentos.data.repository

import com.example.appalimentos.data.model.RecipeInfo
import com.example.appalimentos.data.remote.ApiConstants
import com.example.appalimentos.data.remote.RetrofitClient

class RecipeRepository {

    suspend fun getRecipe(
        id: Int,
        apiKey: String
    ): RecipeInfo {

        val response =
            RetrofitClient.api.getFoodDetails(
                id,
                apiKey
            )

        fun nutrient(name: String): Double? {

            return response.foodNutrients
                ?.firstOrNull {
                    it.nutrient?.name == name
                }
                ?.amount
        }

        val additionalDescription =
            response.foodAttributes
                ?.filter {
                    it.foodAttributeType?.name ==
                            "Additional Description"
                }
                ?.joinToString(", ") {
                    it.value ?: ""
                }

        return RecipeInfo(

            fdcId = id,

            name = response.description ?: "Sin nombre",

            category =
                response.wweiaFoodCategory
                    ?.wweiaFoodCategoryDescription,

            additionalDescription =
                additionalDescription,

            calories = nutrient("Energy"),

            protein = nutrient("Protein"),

            fat = nutrient("Total lipid (fat)"),

            carbs =
                nutrient("Carbohydrate, by difference"),

            sugars = nutrient("Total Sugars")
        )
    }
}