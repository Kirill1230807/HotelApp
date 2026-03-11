package com.example.hotelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.hotelapp.presentation.screens.favourites.FavouriteScreen
import com.example.hotelapp.presentation.screens.main.MainScreen
import com.example.hotelapp.presentation.viewmodel.HotelListViewModel


/** Основний вузол навігації. Зв'язує всі екрани для можливості перемикатися між ними. **/
@Composable
fun AppNavigation(
    viewModel: HotelListViewModel = viewModel()
) {
    // Об'єкт, який пам'ятає, де знаходиться користувач і вміє перемикати екрани.
    val navController = rememberNavController()


    /* Карта маршрутів. Передаємо наш navController, зазначаємо початковий екран
    * через startDestination*/

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Блок composable позначаємо екрани, між якими можна перемикатись
        composable(Screen.Home.route) {
            MainScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screen.Favourites.route) {
            FavouriteScreen(viewModel, navController)
        }
    }
}