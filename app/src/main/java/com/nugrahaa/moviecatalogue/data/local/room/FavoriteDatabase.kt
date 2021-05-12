package com.nugrahaa.moviecatalogue.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity

@Database(entities = [FavMovieEntity::class, FavTvShowEntity::class], version = 4, exportSchema = false)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "Favoriteku.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}