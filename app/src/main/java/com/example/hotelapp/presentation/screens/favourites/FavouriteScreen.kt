package com.example.hotelapp.presentation.screens.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelapp.R
import com.example.hotelapp.domain.model.Hotel
import com.example.hotelapp.presentation.components.hotelCard.HotelCard
import com.example.hotelapp.presentation.theme.AdditionalColor
import com.example.hotelapp.presentation.theme.MainColor
import com.example.hotelapp.presentation.viewmodel.HotelListViewModel
import com.example.hotelapp.util.HotelUIState

@Composable
fun FavouriteScreen(
    viewModel: HotelListViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val favourites by viewModel.favouriteHotelState.collectAsState()

    FavouriteScreenContent(
        uiState = uiState,
        favouriteHotelIds = favourites,
        onFavouriteClick = { hotelId -> viewModel.onFavourite(hotelId) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreenContent(
    uiState: HotelUIState,
    favouriteHotelIds: Set<Int>,
    onFavouriteClick: (Int) -> Unit
) {

    when (uiState) {
        is HotelUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MainColor)
            }
        }

        is HotelUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.message, color = Color.Red)
            }
        }

        is HotelUIState.Success -> {
            val favouriteHotels = uiState.hotels.filter { hotel ->
                favouriteHotelIds.contains(hotel.id)
            }

            if (favouriteHotels.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AdditionalColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "У вас поки немає збережених готелів",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AdditionalColor),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(favouriteHotels) { hotel ->
                        HotelCard(
                            hotel = hotel,
                            isFavourite = true,
                            onFavouriteClick = { onFavouriteClick(hotel.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FavouriteScreenPreview() {
    val mockHotels = listOf(
        Hotel(
            id = 1, name = "Premier Palace", description = "", city = "Київ", address = "",
            rating = 4.7, reviewCount = 56, pricePerNight = 2500.0, currency = "UAH",
            imageUrls = listOf(R.drawable.hotel), amenities = emptyList(), coordinates = ""
        ),
        Hotel(
            id = 2, name = "Hilton", description = "", city = "Київ", address = "",
            rating = 4.9, reviewCount = 120, pricePerNight = 5000.0, currency = "UAH",
            imageUrls = listOf(R.drawable.hotel1), amenities = emptyList(), coordinates = ""
        )
    )
    FavouriteScreenContent(
        uiState = HotelUIState.Success(mockHotels),
        favouriteHotelIds = setOf(1, 2),
        onFavouriteClick = { }
    )
}