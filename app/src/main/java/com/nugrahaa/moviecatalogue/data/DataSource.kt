package com.nugrahaa.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bumptech.glide.load.engine.Resource
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow

interface DataSource {

    fun getAllMovies(): LiveData<ArrayList<Movie?>>

    fun getAllTvShow(): LiveData<ArrayList<TVShow?>>

    fun getMoviesById(id: String): LiveData<Movie>

    fun getTVShowById(id: String): LiveData<TVShow>

    fun getFavMovie(): LiveData<PagedList<FavMovieEntity>>

    fun getFavTvShow(): LiveData<PagedList<FavTvShowEntity>>

    suspend fun addMovieToFavorite(movie: Movie)

    suspend fun deleteMovieFromFavorite(movie: Movie)

    suspend fun addTVShowToFavorite(tvShow: TVShow)

    suspend fun deleteTVShowFromFavorite(tvShow: TVShow)

}