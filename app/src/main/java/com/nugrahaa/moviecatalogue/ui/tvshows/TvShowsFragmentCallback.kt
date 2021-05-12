package com.nugrahaa.moviecatalogue.ui.tvshows

import com.nugrahaa.moviecatalogue.data.remote.response.TVShow

interface TvShowsFragmentCallback {

    fun onClickGotoDetail(tvShowEntity: TVShow?)

}