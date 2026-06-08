package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.FoodDao
import com.example.appalimentos.data.local.FoodEntity

class FoodLocalRepository(
    private val dao: FoodDao
) {

    suspend fun saveFood(food: FoodEntity) {
        dao.insertFood(food)
    }

    suspend fun getFoods(userEmail: String): List<FoodEntity> {
        return dao.getFoodsByUser(userEmail)
    }

    suspend fun deleteFood(foodId: Int, userEmail: String) {
        dao.deleteFood(foodId, userEmail)
    }
}