package com.nugrahaa.moviecatalogue.ui.favmovies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.ui.detail.DetailActivity
import com.nugrahaa.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_movies.*

class FavMoviesFragment : Fragment(), FavMoviesFragmentCallback {

    private lateinit var viewModel: FavMoviesViewModel
    private lateinit var rvMovie: RecyclerView
    private lateinit var listFavMovieAdapter: FavMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fav_movie_progress.visibility = View.VISIBLE
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[FavMoviesViewModel::class.java]

            viewModel.getFavMovies()
            attachObserver()
        }
    }

    private fun attachObserver() {
        viewModel.getFavMovies().observe(viewLifecycleOwner, Observer {
            fav_movie_progress.visibility = View.GONE
            listFavMovieAdapter.submitList(it)
        })
        rvMovie = fav_rv_movies
        rvMovie.layoutManager = LinearLayoutManager(context)
        listFavMovieAdapter = FavMoviesAdapter(this)
        rvMovie.adapter = listFavMovieAdapter
    }

    override fun onClickGoToDetail(movie: FavMovieEntity?) {
        val mIntent = Intent(context, DetailActivity::class.java)
        mIntent.putExtra("TYPE", "movie")
        mIntent.putExtra("ID", movie?.id)
        startActivity(mIntent)
    }
}
