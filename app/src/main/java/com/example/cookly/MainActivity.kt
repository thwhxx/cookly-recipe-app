package com.example.cookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cookly.features.recipe.CooklyNav
import com.example.cookly.ui.theme.CooklyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CooklyTheme { CooklyNav() } }
    }
}
