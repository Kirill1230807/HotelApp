package com.example.hotelapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_hotels")
data class FavouriteHotelEntity(
    @PrimaryKey val hotelId: Int
)
