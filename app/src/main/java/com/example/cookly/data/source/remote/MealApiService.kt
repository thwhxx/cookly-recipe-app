package com.example.cookly.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    // search by keyword
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse

    // lookup by id
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealResponse
}
