package com.example.appalimentos.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appalimentos.data.model.NutritionInfo
import com.example.appalimentos.data.remote.ApiConstants
import com.example.appalimentos.data.repository.FoodRepository
import kotlinx.coroutines.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import com.example.appalimentos.data.local.FoodDatabaseProvider
import com.example.appalimentos.data.local.FoodEntity
import com.example.appalimentos.data.local.RecipeEntity
import com.example.appalimentos.data.repository.FoodLocalRepository
import com.example.appalimentos.data.repository.RecipeLocalRepository

class FoodViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = FoodRepository()

    private val localRepository =
        FoodLocalRepository(
            FoodDatabaseProvider
                .getDatabase(application)
                .foodDao()
        )

    private val recipeRepository =
        RecipeLocalRepository(
            FoodDatabaseProvider
                .getDatabase(application)
                .recipeDao()
        )

    var food = mutableStateOf<NutritionInfo?>(null)
        private set

    var savedFoods = mutableStateOf<List<FoodEntity>>(emptyList())
        private set

    var savedRecipes =
        mutableStateOf<List<RecipeEntity>>(emptyList())
        private set

    fun loadFood(id: Int) {
        viewModelScope.launch {
            food.value = repository.getNutrition(
                id,
                ApiConstants.API_KEY
            )
        }
    }

    fun saveCurrentFood(userEmail: String) {
        val currentFood = food.value ?: return

        viewModelScope.launch {
            localRepository.saveFood(
                FoodEntity(
                    fdcId = currentFood.fdcId,
                    userEmail = userEmail,
                    name = currentFood.name,
                    dataType = currentFood.dataType,
                    category = currentFood.category,
                    brand = currentFood.brand,
                    calories = currentFood.calories,
                    protein = currentFood.protein,
                    fat = currentFood.fat,
                    carbs = currentFood.carbs,
                    sugars = currentFood.sugars
                )
            )
        }
    }

    fun loadSavedFoods(userEmail: String) {
        viewModelScope.launch {
            savedFoods.value =
                localRepository.getFoods(userEmail)
        }
    }

    fun deleteFood(foodId: Int, userEmail: String) {
        viewModelScope.launch {
            localRepository.deleteFood(foodId, userEmail)
            loadSavedFoods(userEmail)
        }
    }

    fun saveCurrentRecipe(userEmail: String) {
        val currentRecipe = food.value ?: return

        viewModelScope.launch {
            recipeRepository.saveRecipe(
                RecipeEntity(
                    fdcId = currentRecipe.fdcId,
                    userEmail = userEmail,
                    name = currentRecipe.name,
                    dataType = currentRecipe.dataType,
                    category = currentRecipe.category,
                    calories = currentRecipe.calories,
                    protein = currentRecipe.protein,
                    fat = currentRecipe.fat,
                    carbs = currentRecipe.carbs,
                    sugars = currentRecipe.sugars,
                    additionalDescription =
                        currentRecipe.additionalDescription
                )
            )
        }
    }

    fun loadSavedRecipes(userEmail: String) {
        viewModelScope.launch {
            savedRecipes.value =
                recipeRepository.getRecipes(userEmail)
        }
    }

    fun deleteRecipe(recipeId: Int, userEmail: String) {
        viewModelScope.launch {
            recipeRepository.deleteRecipe(recipeId, userEmail)
            loadSavedRecipes(userEmail)
        }
    }
}
