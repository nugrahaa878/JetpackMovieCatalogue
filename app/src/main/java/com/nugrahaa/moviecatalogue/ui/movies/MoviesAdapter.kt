package com.nugrahaa.moviecatalogue.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import kotlinx.android.synthetic.main.items_movies.view.*

class MoviesAdapter(
    private val listMovie: ArrayList<Movie?>,
    private val callback: MoviesFragmentCallback
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie?) {
            with(itemView) {
                tv_movie_title.text = movie?.originalTitle
                tv_movie_date.text = movie?.releaseDate
                tv_movie_description.text = movie?.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + movie?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster_movies)

                itemView.setOnClickListener {
                    callback.onClickGotoDetail(movie)
                }
            }
        }
    }
}