package com.nugrahaa.moviecatalogue.ui.favtvshows

import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity

interface FavTvShowFragmentCallback {

    fun onClickGoToDetail(tvShow: FavTvShowEntity?)

}