package com.example.hotelapp.domain.reposityry

import com.example.hotelapp.domain.model.Hotel

interface HotelRepository {
    suspend fun getAllHotels(): List<Hotel>
}