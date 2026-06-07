package com.example.appalimentos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodEntity)

    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntity>

    @Query("DELETE FROM foods WHERE fdcId = :foodId")
    suspend fun deleteFood(foodId: Int)
}
