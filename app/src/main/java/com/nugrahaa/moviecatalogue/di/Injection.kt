package com.nugrahaa.moviecatalogue.di

import android.content.Context
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.local.LocalDataSource
import com.nugrahaa.moviecatalogue.data.local.room.FavoriteDatabase
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): Repository {

        val database = FavoriteDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())

        return Repository.getInstance(remoteDataSource, localDataSource)
    }

}