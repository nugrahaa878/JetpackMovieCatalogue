package com.nugrahaa.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.local.room.FavoriteDatabase
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var movie: Movie
    private lateinit var tvShow: TVShow
    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getType = intent.getStringExtra("TYPE")
        val getId = intent.getIntExtra("ID", -1)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailActivityViewModel::class.java]

        if (getType == "movie") {
            viewModel.getMoviesById(getId.toString())
            attachObserverMovie(getId.toString())
            supportActionBar?.title = "About Movie"
        } else if (getType == "tvshow") {
            viewModel.getTVShowById(getId.toString())
            attachObserverTvShow(getId.toString())
            supportActionBar?.title = "About TV Show"
        }

        floating_favorite.setOnClickListener {
            try {
                if (getType == "tvshow") {
                    checkLiked("tvshow")
                    insertTvShowToDatabase()
                } else {
                    checkLiked("movie")
                    insertMovieToDatabase()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkLiked(tipe: String) {
        val db = FavoriteDatabase.getInstance(applicationContext)
        val dao = db.favoriteDao()

        if (tipe == "movie") {
            dao.getMovieByTitle(movie.title).observe(this, Observer { liveMovieData ->
                if (liveMovieData.isNotEmpty() && liveMovieData[0].title != null) {
                    Log.d("FAVORITE", liveMovieData[0].title.toString())
                    isFav = true
                    changeFabIcon(isFav)
                } else {
                    isFav = false
                    changeFabIcon(isFav)
                }
            })
        } else {
            dao.getTvShowByName(tvShow.name).observe(this, Observer { liveTvShowdata ->
                if (liveTvShowdata.isNotEmpty() && liveTvShowdata[0].name != null) {
                    isFav = true
                    changeFabIcon(isFav)
                } else {
                    isFav = false
                    changeFabIcon(isFav)
                }

            })
        }
    }

    private fun changeFabIcon(fav: Boolean) {
        if (fav) {
            floating_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.heart_pink))
        } else {
            floating_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.heart_black_2))
        }
    }

    private fun insertMovieToDatabase() {
        try {
            if (isFav) {
                viewModel.deleteMovieFromFav(movie)
                Toast.makeText(this, "Success delete from Favorite Movie", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertMovieToFav(movie)
                Toast.makeText(this, "Success insert to Favorite Movie", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertTvShowToDatabase() {
        try {
            if (isFav) {
                viewModel.deleteTvShowFromFav(tvShow)
                Toast.makeText(this, "Success delete from Favorite TVShow", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertFavTvShowToFav(tvShow)
                Toast.makeText(this, "Success insert to Favorite TVShow", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun attachObserverMovie(id: String) {
        viewModel.getMoviesById(id).observe(this, Observer {
            detail_progress.visibility = View.GONE
            addMovieToView(it)
            checkLiked("movie")
        })
    }

    private fun attachObserverTvShow(id: String) {
        viewModel.getTVShowById(id).observe(this, Observer {
            detail_progress.visibility = View.GONE
            addTvShowToView(it)
            checkLiked("tvshow")
        })
    }

    private fun addMovieToView(movie: Movie) {
        tv_title_detail.text = movie.title
        tv_date_detail.text = movie.releaseDate
        tv_userscore_detail.text = (movie.voteAverage?.toInt()?.times(100)).toString()
        tv_description_detail.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_detail)

        val userScore = (movie.voteAverage?.toInt()?.times(10))?.toFloat()
        if (userScore != null) {
            circularProgressBar.apply {
                progress = userScore
                setProgressWithAnimation(userScore, 10000)
                progressMax = 100f
            }
        }

        this.movie = movie
    }

    private fun addTvShowToView(tvShow: TVShow) {
        tv_title_detail.text = tvShow.name
        tv_date_detail.text = tvShow.firstAirDate
        tv_userscore_detail.text = tvShow.voteCount.toString()
        tv_description_detail.text = tvShow.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + tvShow.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(img_detail)

        val userScore = (tvShow.voteAverage?.toInt()?.times(10))?.toFloat()
        if (userScore != null) {
            circularProgressBar.apply {
                progress = userScore
                setProgressWithAnimation(userScore, 10000)
                progressMax = 100f
            }
        }

        this.tvShow = tvShow
    }
}