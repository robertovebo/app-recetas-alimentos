package com.example.appalimentos.viewmodel


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appalimentos.data.model.FoodSearchItem
import com.example.appalimentos.data.repository.FoodRepository
import kotlinx.coroutines.launch

class FoodSearchViewModel : ViewModel() {

    private val repository = FoodRepository()

    var searchResults = mutableStateListOf<FoodSearchItem>()
        private set

    fun searchFoods(query: String) {

        viewModelScope.launch {

            try {

                val foods = repository.searchFoods(query)

                searchResults.clear()
                searchResults.addAll(foods)

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }
    }
}