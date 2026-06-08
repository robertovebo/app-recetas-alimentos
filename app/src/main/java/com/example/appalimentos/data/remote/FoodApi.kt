package com.example.appalimentos.data.remote

import com.example.appalimentos.data.model.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import com.example.appalimentos.data.model.FoodSearchResponse
import com.example.appalimentos.data.model.SearchRequest
import retrofit2.http.Body
import retrofit2.http.POST

// interfaz de retrofit
interface FoodApi {

    // buscar por id
    @GET("food/{fdcId}")
    suspend fun getFoodDetails(
        @Path("fdcId") id: Int,
        @Query("api_key") apiKey: String
    ): FoodResponse

    // buscar por nombre
    @POST("foods/search")
    suspend fun searchFoods(
        @Query("api_key") apiKey: String,
        @Body request: SearchRequest
    ): FoodSearchResponse
}