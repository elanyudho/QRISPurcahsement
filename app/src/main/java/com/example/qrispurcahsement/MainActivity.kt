package com.example.qrispurcahsement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.qrispurcahsement.presentation.screens.MainScreen
import com.example.qrispurcahsement.ui.theme.QRISPurcahsementTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRISPurcahsementTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}