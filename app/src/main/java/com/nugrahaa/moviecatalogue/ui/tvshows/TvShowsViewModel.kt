package com.nugrahaa.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow

class TvShowsViewModel(private val repository: Repository) : ViewModel() {

    fun getTvShow(): LiveData<ArrayList<TVShow?>> {
        return repository.getAllTvShow()
    }

}