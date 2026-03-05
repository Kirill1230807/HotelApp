package com.example.hotelapp.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotelapp.R
import com.example.hotelapp.domain.model.Hotel
import com.example.hotelapp.presentation.components.hotelCard.HotelCard
import com.example.hotelapp.presentation.components.main.Header
import com.example.hotelapp.presentation.components.main.SortDropdownMenu
import com.example.hotelapp.presentation.screens.search.HotelSearchCard
import com.example.hotelapp.presentation.theme.*
import com.example.hotelapp.presentation.viewmodel.HotelListViewModel
import com.example.hotelapp.util.Amenity
import com.example.hotelapp.util.HotelUIState

@Composable
fun MainScreen(
    viewModel: HotelListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MainScreenContent(uiState = uiState)
}

@Composable
fun MainScreenContent(uiState: HotelUIState) {
    Scaffold(
        topBar = { Header() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .drawBehind {
                    drawRect(color = Color.White)
                    drawRect(
                        color = MainColor,
                        size = Size(size.width, size.height / 3.5f)
                    )
                }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    Spacer(Modifier.height(20.dp))
                    HotelSearchCard()
                    Spacer(Modifier.height(16.dp))
                    SortDropdownMenu(onSortSelected = {})
                }

                when (uiState) {
                    is HotelUIState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = MainColor)
                            }
                        }
                    }

                    is HotelUIState.Success -> {
                        items(uiState.hotels) { hotel ->
                            HotelCard(hotel = hotel)
                        }
                    }

                    is HotelUIState.Error -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = uiState.message,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val mockHotels = listOf(
        Hotel(
            id = 1,
            name = "Premier Palace",
            description = "Преміальний готель Києва",
            city = "Київ",
            address = "вул. Головна, 1",
            rating = 4.7,
            reviewCount = 56,
            pricePerNight = 2500.0,
            currency = "UAH",
            imageUrls = listOf(R.drawable.hotel, R.drawable.hotel1, R.drawable.hotel2),
            amenities = listOf(Amenity.RESTAURANT, Amenity.WI_FI),
            coordinates = ""
        ),
        Hotel(
            id = 2,
            name = "Premier Palace",
            description = "Преміальний готель Києва",
            city = "Київ",
            address = "вул. Головна, 1",
            rating = 4.7,
            reviewCount = 56,
            pricePerNight = 2500.0,
            currency = "UAH",
            imageUrls = listOf(R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel),
            amenities = listOf(Amenity.RESTAURANT, Amenity.WI_FI),
            coordinates = ""
        ),
        Hotel(
            id = 3,
            name = "Premier Palace",
            description = "Преміальний готель Києва",
            city = "Київ",
            address = "вул. Головна, 1",
            rating = 4.7,
            reviewCount = 56,
            pricePerNight = 2500.0,
            currency = "UAH",
            imageUrls = listOf(R.drawable.hotel2, R.drawable.hotel, R.drawable.hotel1),
            amenities = listOf(Amenity.RESTAURANT, Amenity.WI_FI),
            coordinates = ""
        )
    )
    MainScreenContent(uiState = HotelUIState.Success(mockHotels))
}