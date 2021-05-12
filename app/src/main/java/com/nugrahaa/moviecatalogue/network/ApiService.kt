package com.nugrahaa.moviecatalogue.network

import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.ResponseMovie
import com.nugrahaa.moviecatalogue.data.remote.response.ResponseTvShow
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovieData(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Flowable<ResponseMovie>

    @GET("tv/popular")
    fun getTvShowData(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Flowable<ResponseTvShow>

    @GET("movie/{id}")
    fun getMovieById(
        @Path("id") id: String,
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Flowable<Movie>

    @GET("tv/{id}")
    fun getTvShowById(
        @Path("id") id: String,
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Flowable<TVShow>

}