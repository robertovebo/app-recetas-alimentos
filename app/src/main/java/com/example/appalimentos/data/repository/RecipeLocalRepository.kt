package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.RecipeDao
import com.example.appalimentos.data.local.RecipeEntity

class RecipeLocalRepository(
    private val dao: RecipeDao
) {

    suspend fun saveRecipe(recipe: RecipeEntity) {
        dao.insertRecipe(recipe)
    }

    suspend fun getRecipes(userEmail: String): List<RecipeEntity> {
        return dao.getRecipesByUser(userEmail)
    }

    suspend fun deleteRecipe(recipeId: Int, userEmail: String) {
        dao.deleteRecipe(recipeId, userEmail)
    }
}