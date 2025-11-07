package com.example.cookly.data.source.repository

import com.example.cookly.data.source.remote.MealDto
import com.example.cookly.data.source.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepository {
    private val api = RetrofitClient.api

    suspend fun searchMeals(query: String): List<MealDto> = safeIo {
        api.searchMeals(query).meals ?: emptyList()
    }

    suspend fun getMeal(id: String): MealDto? = safeIo {
        api.getMealById(id).meals?.firstOrNull()
    }

    private suspend fun <T> safeIo(block: suspend () -> T): T =
        withContext(Dispatchers.IO) {
            try { block() } catch (_: Exception) { // nuốt lỗi để UI không crash
                @Suppress("UNCHECKED_CAST")
                (when (null) { else -> emptyList<Any>() } as? T) ?: null as T
            }
        }
}
