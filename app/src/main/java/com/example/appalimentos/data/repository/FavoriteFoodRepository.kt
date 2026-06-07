package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.FoodDao
import com.example.appalimentos.data.local.FoodEntity

class FavoriteFoodRepository(
    private val foodDao: FoodDao
) {

    suspend fun saveFood(food: FoodEntity) {
        foodDao.insertFood(food)
    }

    suspend fun getFoods(): List<FoodEntity> {
        return foodDao.getAllFoods()
    }

    suspend fun deleteFood(foodId: Int) {
        foodDao.deleteFood(foodId)
    }
}