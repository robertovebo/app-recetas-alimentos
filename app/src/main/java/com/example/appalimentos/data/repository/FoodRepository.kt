package com.example.appalimentos.data.repository

import com.example.appalimentos.data.model.FoodSearchItem
import com.example.appalimentos.data.model.NutritionInfo
import com.example.appalimentos.data.model.SearchRequest
import com.example.appalimentos.data.remote.RetrofitClient

import com.example.appalimentos.data.remote.ApiConstants


class FoodRepository {

    suspend fun getNutrition(id: Int, apiKey: String): NutritionInfo {

        val response = RetrofitClient.api.getFoodDetails(
            id,
            ApiConstants.API_KEY
        )
        fun findValue(name: String): Double? {
            return response.foodNutrients?.find {
                it.nutrient?.name?.contains(name, ignoreCase = true) == true
            }?.amount
        }

        return NutritionInfo(
            fdcId = id,
            name = response.description ?: "Sin nombre",
            dataType = response.dataType,
            category =
                response.brandedFoodCategory
                    ?: response.foodCategory?.description,
            brand = response.brandOwner,
            calories = findValue("Energy"),
            protein = findValue("Protein"),
            fat = findValue("Total lipid"),
            carbs = findValue("Carbohydrate"),
            sugars = findValue("Total Sugars")
        )
    }

    suspend fun searchFoods(query: String): List<FoodSearchItem> {

        val response = RetrofitClient.api.searchFoods(
            ApiConstants.API_KEY,
            SearchRequest(query)
        )

        return response.foods
    }
}