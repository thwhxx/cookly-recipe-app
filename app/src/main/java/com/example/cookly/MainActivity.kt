package com.example.cookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cookly.components.BottomNavigationBar
import com.example.cookly.features.recipe.CooklyNav
import com.example.cookly.screens.SplashScreen
import com.example.cookly.ui.theme.CooklyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CooklyTheme {
                AppRoot()
//                AppNavigation()
            }
        }
    }
}

@Composable
fun AppRoot() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(onTimeout = { showSplash = false })
    } else {
        MainAppWithNavBar()
    }
}

@Composable
fun MainAppWithNavBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        CooklyNav(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

//@Composable
//fun AppNavigation(modifier: Modifier = Modifier) {
//    var showSplash by remember { mutableStateOf(true) }
//    if (showSplash) {
//        SplashScreen(onTimeout = { showSplash = false })
//    } else {
//        CooklyNav()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    CooklyTheme() {
//       SplashScreen(onTimeout = {})
//    }
//}