package com.nugrahaa.moviecatalogue.ui.favmovies

import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity

interface FavMoviesFragmentCallback {

    fun onClickGoToDetail(movie: FavMovieEntity?)

}