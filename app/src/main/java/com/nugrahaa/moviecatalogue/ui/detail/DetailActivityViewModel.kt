package com.nugrahaa.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivityViewModel(private val repository: Repository) : ViewModel() {

    fun getMoviesById(id: String): LiveData<Movie> {
        return repository.getMoviesById(id)
    }

    fun getTVShowById(id: String): LiveData<TVShow> {
        return repository.getTVShowById(id)
    }

    fun insertMovieToFav(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovieToFavorite(movie)
        }
    }

    fun insertFavTvShowToFav(tvShow: TVShow) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTVShowToFavorite(tvShow)
        }
    }

    fun deleteMovieFromFav(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovieFromFavorite(movie)
        }
    }

    fun deleteTvShowFromFav(tvShow: TVShow) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTVShowFromFavorite(tvShow)
        }
    }

}