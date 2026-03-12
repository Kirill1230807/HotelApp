package com.example.hotelapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Головна", Icons.Default.Home)
    object Favourites : Screen("favourites", "Обране", Icons.Default.Favorite)
    object Search : Screen("search", "Пошук", Icons.Default.Search)
}