package com.example.appalimentos.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appalimentos.data.model.NutritionInfo
import com.example.appalimentos.data.remote.ApiConstants
import com.example.appalimentos.data.repository.FoodRepository
import kotlinx.coroutines.launch

import android.app.Application //***********   Para dar acceso a la room
import androidx.lifecycle.AndroidViewModel

import com.example.appalimentos.data.local.FoodDatabaseProvider
import com.example.appalimentos.data.local.FoodEntity
import com.example.appalimentos.data.local.RecipeEntity
import com.example.appalimentos.data.repository.FoodLocalRepository //*******
import com.example.appalimentos.data.repository.RecipeLocalRepository

class FoodViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = FoodRepository()

    // bdd alimentos
    private val localRepository =
        FoodLocalRepository(
            FoodDatabaseProvider
                .getDatabase(application)
                .foodDao()
        )

    //bdd recetas
    private val recipeRepository =
        RecipeLocalRepository(
            FoodDatabaseProvider
                .getDatabase(application)
                .recipeDao()
        )

    var food = mutableStateOf<NutritionInfo?>(null)
        private set

    //alimentos
    var savedFoods = mutableStateOf<List<FoodEntity>>(emptyList())
        private set

    //recetas
    var savedRecipes =
        mutableStateOf<List<RecipeEntity>>(emptyList())
        private set

    // Carga de alimentos desde la API
    fun loadFood(id: Int) {
        viewModelScope.launch {

            food.value = repository.getNutrition(
                id,
                ApiConstants.API_KEY
            )

        }
    }

    // Guarda alimentos en la Room
    fun saveCurrentFood() {
        val currentFood = food.value ?: return

        viewModelScope.launch {
            localRepository.saveFood(

                FoodEntity(
                    fdcId = currentFood.fdcId,
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

    // Carga los alimentos guardados en la Room
    fun loadSavedFoods() {
        viewModelScope.launch {
            savedFoods.value =
                localRepository.getFoods()

        }
    }

    fun deleteFood(foodId: Int) {
        viewModelScope.launch {
            localRepository.deleteFood(foodId)

            loadSavedFoods()
        }
    }

    fun saveCurrentRecipe() {
        val currentRecipe = food.value ?: return

        viewModelScope.launch {

            recipeRepository.saveRecipe(

                RecipeEntity(
                    fdcId = currentRecipe.fdcId,
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

    fun loadSavedRecipes() {
        viewModelScope.launch {
            savedRecipes.value =
                recipeRepository.getRecipes()
        }
    }

    fun deleteRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeRepository.deleteRecipe(recipeId)
            loadSavedRecipes()
        }
    }
}