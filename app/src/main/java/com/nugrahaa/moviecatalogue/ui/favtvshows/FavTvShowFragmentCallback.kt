package com.nugrahaa.moviecatalogue.ui.favtvshows

import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow

interface FavTvShowFragmentCallback {

    fun onClickGoToDetail(tvShow: FavTvShowEntity?)

}