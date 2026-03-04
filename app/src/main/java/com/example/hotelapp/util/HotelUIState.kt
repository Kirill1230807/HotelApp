package com.example.hotelapp.util

import com.example.hotelapp.domain.model.Hotel

sealed interface HotelUIState {
    object Loading : HotelUIState
    data class Success(val hotels: List<Hotel>) : HotelUIState
    data class Error(val message: String) : HotelUIState
}