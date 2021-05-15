package com.nugrahaa.moviecatalogue.ui.favtvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity

class FavTvShowViewModel(private val repository: Repository): ViewModel() {

    fun getFavTvShow(): LiveData<PagedList<FavTvShowEntity>> = repository.getFavTvShow()

}