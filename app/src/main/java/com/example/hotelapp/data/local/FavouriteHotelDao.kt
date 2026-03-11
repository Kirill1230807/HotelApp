package com.example.hotelapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteHotelDao {

    // Отримати всі готелі
    @Query("SELECT hotelId FROM favourite_hotels")
    fun getAllFavouriteIds(): Flow<List<Int>>

    /* Додати готель в обране
    * (блок коду (onConflict = OnConflictStrategy.REPLACE))
    * якщо такий готель вже є - замінити */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteHotelEntity): Long

    // Видалити готель
    @Delete
    suspend fun deleteFavourite(favourite: FavouriteHotelEntity): Int
}