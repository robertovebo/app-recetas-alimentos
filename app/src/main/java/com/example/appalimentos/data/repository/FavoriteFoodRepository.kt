package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.FoodDao
import com.example.appalimentos.data.local.FoodEntity

class FavoriteFoodRepository(
    private val foodDao: FoodDao
) {

    suspend fun saveFood(food: FoodEntity) {
        foodDao.insertFood(food)
    }

    suspend fun getFoods(userEmail: String): List<FoodEntity> {
        return foodDao.getFoodsByUser(userEmail)
    }

    suspend fun deleteFood(foodId: Int, userEmail: String) {
        foodDao.deleteFood(foodId, userEmail)
    }
}
