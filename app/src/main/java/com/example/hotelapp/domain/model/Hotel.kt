package com.example.hotelapp.domain.model

import com.example.hotelapp.util.Amenity
import com.google.android.gms.maps.model.LatLng

data class Hotel(
    val id: Int,
    val name: String,
    val description: String,
    val city: String,
    val address: String,
    val rating: Double,
    val reviewCount: Int,
    val pricePerNight: Double,
    val currency : String = "UAH",
    val imageUrls: List<Int>,
    val amenities: List<Amenity>,
    val coordinates: String
)
