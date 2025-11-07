package com.example.cookly.features.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookly.data.source.remote.MealDto
import com.example.cookly.data.source.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    private val repo = MealRepository()

    private val _meal = MutableStateFlow<MealDto?>(null)
    val meal: StateFlow<MealDto?> = _meal

    fun load(id: String) {
        viewModelScope.launch {
            _meal.value = repo.getMeal(id)
        }
    }
}
