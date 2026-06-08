package com.example.appalimentos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipes WHERE userEmail = :userEmail")
    suspend fun getRecipesByUser(userEmail: String): List<RecipeEntity>

    @Query("DELETE FROM recipes WHERE fdcId = :recipeId AND userEmail = :userEmail")
    suspend fun deleteRecipe(recipeId: Int, userEmail: String)
}