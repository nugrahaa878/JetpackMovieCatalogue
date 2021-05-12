package com.nugrahaa.moviecatalogue.data.local

import androidx.paging.DataSource
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.local.room.FavoriteDao

class LocalDataSource private constructor(private val mFavoriteDao: FavoriteDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }

    fun insertFavMovie(favMovie: FavMovieEntity) = mFavoriteDao.insertFavMovie(favMovie)

    fun deleteMovieFromFavorite(favMovie: FavMovieEntity) = mFavoriteDao.deleteFavMovieByTitle(favMovie.title)

    fun getAllFavMovie(): DataSource.Factory<Int, FavMovieEntity> = mFavoriteDao.getFavMovie()

    fun getAllFavTvShow(): DataSource.Factory<Int, FavTvShowEntity> = mFavoriteDao.getFavTvShow()

    fun insertFavTvShow(favTvShow: FavTvShowEntity) = mFavoriteDao.insertTvShow(favTvShow)

    fun deleteTvShowFromFavorite(favTvShow: FavTvShowEntity) = mFavoriteDao.deleteFavTvShowByName(favTvShow.name)

}