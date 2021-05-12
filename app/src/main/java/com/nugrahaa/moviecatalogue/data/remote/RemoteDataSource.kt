package com.nugrahaa.moviecatalogue.data.remote

import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.ResponseMovie
import com.nugrahaa.moviecatalogue.data.remote.response.ResponseTvShow
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.network.ApiConfig
import com.nugrahaa.moviecatalogue.utils.EspressoIdlingResource
import io.reactivex.rxjava3.core.Flowable

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): Flowable<ResponseMovie> {
        EspressoIdlingResource.increment()
        return ApiConfig.getApiService().getMovieData("b64d761def5c00e40e6a36e0032741bf", "en-US")
    }

    fun getTvShow(): Flowable<ResponseTvShow> {
        EspressoIdlingResource.increment()
        return ApiConfig.getApiService().getTvShowData("b64d761def5c00e40e6a36e0032741bf", "en-US")
    }

    fun getMoviesById(id: String): Flowable<Movie> {
        EspressoIdlingResource.increment()
        return ApiConfig.getApiService()
            .getMovieById(id, "b64d761def5c00e40e6a36e0032741bf", "en-US")
    }

    fun getTVShowById(id: String): Flowable<TVShow> {
        EspressoIdlingResource.increment()
        return ApiConfig.getApiService()
            .getTvShowById(id, "b64d761def5c00e40e6a36e0032741bf", "en-US")
    }
}