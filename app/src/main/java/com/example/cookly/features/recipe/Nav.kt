package com.example.cookly.features.recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cookly.screens.HomeScreen
import com.example.cooklyrecipefinder.features.ProfileScreen
import com.example.cooklyrecipefinder.features.SettingsScreen

@Composable
fun CooklyNav(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen() }
        composable("recipes") { RecipeScreen(onOpen = { id -> navController.navigate("detail/$id") }) }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: return@composable
            RecipeDetailScreen(id = id, onBack = { navController.popBackStack() })
        }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
    }
}
