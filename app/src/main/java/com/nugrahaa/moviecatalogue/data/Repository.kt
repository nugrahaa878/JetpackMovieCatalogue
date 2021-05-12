package com.nugrahaa.moviecatalogue.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bumptech.glide.load.engine.Resource
import com.nugrahaa.moviecatalogue.data.local.LocalDataSource
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.utils.EspressoIdlingResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<ArrayList<Movie?>> {
        val moviesResults = MutableLiveData<ArrayList<Movie?>>()
        remoteDataSource.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val moviesList = ArrayList<Movie?>()
                if (it.results != null) {
                    for (item in it.results) {
                        moviesList.add(item)
                    }
                }
                moviesResults.postValue(moviesList)
                EspressoIdlingResource.decrement()
            }, {
                it.printStackTrace()
            })
        return moviesResults
    }

    override fun getAllTvShow(): LiveData<ArrayList<TVShow?>> {
        val tvShowResults = MutableLiveData<ArrayList<TVShow?>>()
        remoteDataSource.getTvShow()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val tvShowList = ArrayList<TVShow?>()
                if (it.results != null) {
                    for (item in it.results) {
                        tvShowList.add(item)
                    }
                }
                    tvShowResults.postValue(tvShowList)
                EspressoIdlingResource.decrement()
            }, {
                it.printStackTrace()
            })
        return tvShowResults
    }

    override fun getMoviesById(id: String): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        remoteDataSource.getMoviesById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieResult.postValue(it)
                EspressoIdlingResource.decrement()
            }, {
                it.printStackTrace()
            })
        return movieResult
    }

    override fun getTVShowById(id: String): LiveData<TVShow> {
        val tvShowResult = MutableLiveData<TVShow>()
        remoteDataSource.getTVShowById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tvShowResult.postValue(it)
                EspressoIdlingResource.decrement()
            }, {
                it.printStackTrace()
            })
        return tvShowResult
    }

    override fun getFavMovie(): LiveData<PagedList<FavMovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavMovie(), config).build()
    }

    override fun getFavTvShow(): LiveData<PagedList<FavTvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavTvShow(), config).build()
    }

    override suspend fun addMovieToFavorite(movie: Movie) {
        try {
            val favMovie = FavMovieEntity(movie.id, movie.title, movie.overview, movie.originalTitle, movie.originalLanguage, movie.voteAverage, movie.popularity, movie.voteCount, movie.releaseDate, movie.backdropPath, movie.posterPath, movie.video, movie.adult)
            localDataSource.insertFavMovie(favMovie)
        } catch (e: Exception) {
            Log.d("ERROR INSERT", e.message.toString())
        }
    }

    override suspend fun deleteMovieFromFavorite(movie: Movie) {
        try {
            val favMovie = FavMovieEntity(movie.id, movie.title, movie.overview, movie.originalTitle, movie.originalLanguage, movie.voteAverage, movie.popularity, movie.voteCount, movie.releaseDate, movie.backdropPath, movie.posterPath, movie.video, movie.adult)
            localDataSource.deleteMovieFromFavorite(favMovie)
        } catch (e: Exception) {
            Log.d("ERROR DELETE", e.message.toString())
        }
    }

    override suspend fun addTVShowToFavorite(tvShow: TVShow) {
        try {
            val favTvShow = FavTvShowEntity(tvShow.id, tvShow.name, tvShow.overview, tvShow.originalName, tvShow.originalLanguage, tvShow.voteAverage, tvShow.popularity, tvShow.voteCount, tvShow.firstAirDate, tvShow.backdropPath, tvShow.posterPath)
            localDataSource.insertFavTvShow(favTvShow)
        } catch (e: Exception) {
            Log.d("ERROR DELETE", e.message.toString())
        }
    }

    override suspend fun deleteTVShowFromFavorite(tvShow: TVShow) {
        try {
            val favTvShow = FavTvShowEntity(tvShow.id, tvShow.name, tvShow.overview, tvShow.originalName, tvShow.originalLanguage, tvShow.voteAverage, tvShow.popularity, tvShow.voteCount, tvShow.firstAirDate, tvShow.backdropPath, tvShow.posterPath)
            localDataSource.deleteTvShowFromFavorite(favTvShow)
        } catch (e: Exception) {
            Log.d("ERROR DELETE", e.message.toString())
        }
    }

}