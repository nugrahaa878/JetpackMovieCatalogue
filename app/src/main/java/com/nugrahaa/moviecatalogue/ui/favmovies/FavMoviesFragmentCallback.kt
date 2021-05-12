package com.nugrahaa.moviecatalogue.ui.favmovies

import com.nugrahaa.moviecatalogue.data.remote.response.Movie

interface FavMoviesFragmentCallback {

    fun onClickGoToDetail(movie: Movie?)

}