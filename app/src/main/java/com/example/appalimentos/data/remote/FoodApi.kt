package com.example.appalimentos.data.remote

import com.example.appalimentos.data.model.FoodResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodApi {

    @GET("food/{fdcId}")
    suspend fun getFoodDetails(
        @Path("fdcId") id: Int,
        @Query("api_key") apiKey: String
    ): FoodResponse
}