package com.example.appalimentos.data.repository

import com.example.appalimentos.data.model.NutritionInfo
import com.example.appalimentos.data.remote.RetrofitClient
import com.example.appalimentos.data.model.FoodResponse


class FoodRepository {

    suspend fun getNutrition(id: Int, apiKey: String): NutritionInfo {

        val response = RetrofitClient.api.getFoodDetails(id, apiKey)

        fun findValue(name: String): Double? {
            return response.foodNutrients?.find {
                it.nutrient?.name?.contains(name, ignoreCase = true) == true
            }?.amount
        }

        return NutritionInfo(
            name = response.description ?: "Sin nombre",
            calories = findValue("Energy"),
            protein = findValue("Protein"),
            fat = findValue("Total lipid"),
            carbs = findValue("Carbohydrate")
        )
    }
}