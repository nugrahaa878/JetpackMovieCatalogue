package com.nugrahaa.moviecatalogue.ui.favmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity

class FavMoviesViewModel(private val repository: Repository): ViewModel() {

    fun getFavMovies(): LiveData<PagedList<FavMovieEntity>> = repository.getFavMovie()
}
