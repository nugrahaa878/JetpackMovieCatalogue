package com.nugrahaa.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import io.reactivex.rxjava3.schedulers.Schedulers

class FakeRepository (private val remoteDataSource: RemoteDataSource): DataSource {

    override fun getAllMovies(): LiveData<ArrayList<Movie?>> {
        val moviesResults = MutableLiveData<ArrayList<Movie?>>()
        remoteDataSource.getMovies()
            .subscribeOn(Schedulers.io())
            .subscribe ({
                val moviesList = ArrayList<Movie?>()
                val hasil = it.results
                if (hasil != null) {
                    for (item in hasil) {
                        moviesList.add(item)
                    }
                }
                moviesResults.postValue(moviesList)
            }, {
                it.printStackTrace()
            })
        return moviesResults
    }

    override fun getAllTvShow(): LiveData<ArrayList<TVShow?>> {
        val tvShowResults = MutableLiveData<ArrayList<TVShow?>>()
        remoteDataSource.getTvShow()
            .subscribeOn(Schedulers.io())
            .subscribe ({
                val tvShowList = ArrayList<TVShow?>()
                val hasil = it.results
                if (hasil != null) {
                    for (item in hasil) {
                        tvShowList.add(item)
                    }
                }
                tvShowResults.postValue(tvShowList)
            }, {
                it.printStackTrace()
            })
        return tvShowResults
    }

    override fun getMoviesById(id: String): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        remoteDataSource.getMoviesById(id)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                movieResult.postValue(it)
            }, {
                it.printStackTrace()
            })
        return movieResult
    }

    override fun getTVShowById(id: String): LiveData<TVShow> {
        val tvShowResult = MutableLiveData<TVShow>()
        remoteDataSource.getTVShowById(id)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                tvShowResult.postValue(it)
            }, {
                it.printStackTrace()
            })
        return tvShowResult
    }
}