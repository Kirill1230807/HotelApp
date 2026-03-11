package com.example.hotelapp.presentation.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourites : Screen("favourites")
}