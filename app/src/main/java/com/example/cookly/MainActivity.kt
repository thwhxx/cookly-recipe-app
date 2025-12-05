package com.example.cookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cookly.features.recipe.CooklyNav
import com.example.cookly.features.recipe.SplashScreen
import com.example.cookly.ui.theme.CooklyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CooklyTheme { AppNavigation() } }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    var showSplash by remember { mutableStateOf(true) }
    if (showSplash) {
        SplashScreen(onTimeout = { showSplash = false })
    } else {
        CooklyNav()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    CooklyTheme() {
//       SplashScreen(onTimeout = {})
//    }
//}