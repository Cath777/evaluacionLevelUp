package com.example.levelupgamerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.levelupgamerapp.ui.navigation.AppNavigation
import com.example.levelupgamerapp.ui.theme.LevelUpGamerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LevelUpGamerAppTheme {
                AppNavigation()
            }
        }
    }
}