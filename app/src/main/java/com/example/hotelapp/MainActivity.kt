package com.example.hotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotelapp.presentation.navigation.AppNavigation
import com.example.hotelapp.presentation.screens.main.MainScreen
import com.example.hotelapp.presentation.theme.HotelAppTheme
import com.example.hotelapp.presentation.viewmodel.HotelListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotelAppTheme {
                val context = LocalContext.current
//                MainScreen()
                AppNavigation(
                    context = context
                )
            }
        }
    }
}