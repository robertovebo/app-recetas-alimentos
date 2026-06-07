package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.FoodDao
import com.example.appalimentos.data.local.FoodEntity

class FoodLocalRepository(
    private val dao: FoodDao
) {

    suspend fun saveFood(food: FoodEntity) {
        dao.insertFood(food)
    }

    suspend fun getFoods(): List<FoodEntity> {
        return dao.getAllFoods()
    }

    suspend fun deleteFood(id: Int) {
        dao.deleteFood(id)
    }
}