package com.example.hotelapp.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [FavouriteHotelEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteHotelDao(): FavouriteHotelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hotel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}