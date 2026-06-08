package com.example.appalimentos.data.repository

import com.example.appalimentos.data.local.RecipeDao
import com.example.appalimentos.data.local.RecipeEntity

class RecipeLocalRepository(
    private val dao: RecipeDao
) {

    suspend fun saveRecipe(recipe: RecipeEntity) {
        dao.insertRecipe(recipe)
    }

    suspend fun getRecipes(): List<RecipeEntity> {
        return dao.getAllRecipes()
    }

    suspend fun deleteRecipe(recipeId: Int) {
        dao.deleteRecipe(recipeId)
    }
}