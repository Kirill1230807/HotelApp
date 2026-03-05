package com.example.hotelapp.data.repositoryImp

import com.example.hotelapp.R
import com.example.hotelapp.domain.model.Hotel
import com.example.hotelapp.domain.reposityry.HotelRepository
import com.example.hotelapp.util.Amenity
import kotlinx.coroutines.delay

class HotelRepositoryImp : HotelRepository {
    override suspend fun getAllHotels(): List<Hotel> {
        delay(1000)
        return listOf(
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
    }

    override suspend fun filterHotelsByPrice(): List<Hotel> {
        return emptyList()
    }
}