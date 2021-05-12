package com.nugrahaa.moviecatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.ui.detail.DetailActivity
import com.nugrahaa.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(), MoviesFragmentCallback {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var rvMovie: RecyclerView
    private lateinit var listMovieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie_progress.visibility = View.VISIBLE
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[MoviesViewModel::class.java]

            viewModel.getMovies()
            attachObserver()
        }
    }

    private fun attachObserver() {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            movie_progress.visibility = View.GONE
            showData(it)
        })
    }

    private fun showData(it: ArrayList<Movie?>) {
        rvMovie = rv_movies
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(context)

        listMovieAdapter = MoviesAdapter(it, this)
        rvMovie.adapter = listMovieAdapter
    }

    override fun onClickGotoDetail(movie: Movie?) {
        val mIntent = Intent(context, DetailActivity::class.java)
        mIntent.putExtra("TYPE", "movie")
        mIntent.putExtra("ID", movie?.id)
        startActivity(mIntent)
    }

}