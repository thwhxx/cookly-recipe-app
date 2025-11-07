package com.example.cookly.features.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookly.data.source.remote.MealDto
import com.example.cookly.data.source.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repo = MealRepository()

    private val _meals = MutableStateFlow<List<MealDto>>(emptyList())
    val meals: StateFlow<List<MealDto>> = _meals

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun search(query: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                _meals.value = repo.searchMeals(query)
                if (_meals.value.isEmpty()) _error.value = "No results for \"$query\""
            } catch (e: Exception) {
                _error.value = "Network error. Please try again."
            } finally {
                _loading.value = false
            }
        }
    }
}
