package com.nugrahaa.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nugrahaa.moviecatalogue.di.Injection
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.ui.detail.DetailActivityViewModel
import com.nugrahaa.moviecatalogue.ui.favmovies.FavMoviesViewModel
import com.nugrahaa.moviecatalogue.ui.movies.MoviesViewModel
import com.nugrahaa.moviecatalogue.ui.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val mRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailActivityViewModel::class.java) -> {
                DetailActivityViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(FavMoviesViewModel::class.java) -> {
                FavMoviesViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown View Model Class: " + modelClass.name)
        }
    }

}