package com.nugrahaa.moviecatalogue.ui.movies

import com.nugrahaa.moviecatalogue.data.remote.response.Movie

interface MoviesFragmentCallback {

    fun onClickGotoDetail(movie: Movie?)

}