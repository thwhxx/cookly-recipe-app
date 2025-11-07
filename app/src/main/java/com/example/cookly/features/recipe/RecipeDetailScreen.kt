package com.example.cookly.features.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import com.example.cookly.data.source.remote.MealDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    id: String,
    onBack: () -> Unit,
    vm: RecipeDetailViewModel = viewModel()
) {
    LaunchedEffect(id) { vm.load(id) }
    val meal = vm.meal.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(meal?.strMeal ?: "Recipe") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { pad ->
        if (meal == null) {
            Box(Modifier.fillMaxSize().padding(pad), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            DetailBody(Modifier.padding(pad).padding(16.dp), meal)
        }
    }
}

@Composable
private fun DetailBody(mod: Modifier, meal: MealDto) {
    val ingredients = buildIngredients(meal)
    LazyColumn(mod, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(8.dp))
            Text("Category: ${meal.strCategory ?: "-"} • Area: ${meal.strArea ?: "-"}")
        }
        item { Text("Ingredients", style = MaterialTheme.typography.titleMedium) }
        items(ingredients) { Text("• $it") }
        item { Text("Instructions", style = MaterialTheme.typography.titleMedium) }
        item { Text(meal.strInstructions.orEmpty()) }
    }
}

private fun buildIngredients(m: MealDto): List<String> {
    val pairs = listOf(
        m.strIngredient1 to m.strMeasure1,   m.strIngredient2 to m.strMeasure2,
        m.strIngredient3 to m.strMeasure3,   m.strIngredient4 to m.strMeasure4,
        m.strIngredient5 to m.strMeasure5,   m.strIngredient6 to m.strMeasure6,
        m.strIngredient7 to m.strMeasure7,   m.strIngredient8 to m.strMeasure8,
        m.strIngredient9 to m.strMeasure9,   m.strIngredient10 to m.strMeasure10,
        m.strIngredient11 to m.strMeasure11, m.strIngredient12 to m.strMeasure12,
        m.strIngredient13 to m.strMeasure13, m.strIngredient14 to m.strMeasure14,
        m.strIngredient15 to m.strMeasure15, m.strIngredient16 to m.strMeasure16,
        m.strIngredient17 to m.strMeasure17, m.strIngredient18 to m.strMeasure18,
        m.strIngredient19 to m.strMeasure19, m.strIngredient20 to m.strMeasure20
    )
    return pairs.mapNotNull { (ing, mea) ->
        val i = ing?.trim().orEmpty()
        val m2 = mea?.trim().orEmpty()
        if (i.isNotEmpty()) "${if (m2.isNotEmpty()) "$m2 " else ""}$i" else null
    }
}
