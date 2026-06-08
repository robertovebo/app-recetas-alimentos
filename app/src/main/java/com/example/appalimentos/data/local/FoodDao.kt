package com.example.appalimentos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodEntity)

    //SE ACTUALIZA LA SENTENCIA PARA VALIDAR EL CORREO DEL USUARIO
    @Query("SELECT * FROM foods WHERE userEmail = :userEmail")
    suspend fun getFoodsByUser(userEmail: String): List<FoodEntity>

    //SE ACTUALIZA LA SENTENCIA PARA VALIDAR EL CORREO DEL USUARIO
    @Query("DELETE FROM foods WHERE fdcId = :foodId AND userEmail = :userEmail")
    suspend fun deleteFood(foodId: Int, userEmail: String)
}
