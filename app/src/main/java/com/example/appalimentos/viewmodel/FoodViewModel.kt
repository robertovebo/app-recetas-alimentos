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
import com.example.appalimentos.data.repository.FoodLocalRepository //*******

class FoodViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = FoodRepository()

    // Room
    private val localRepository =
        FoodLocalRepository(
            FoodDatabaseProvider
                .getDatabase(application)
                .foodDao()
        )

    var food = mutableStateOf<NutritionInfo?>(null)
        private set

    var savedFoods = mutableStateOf<List<FoodEntity>>(emptyList())
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
}