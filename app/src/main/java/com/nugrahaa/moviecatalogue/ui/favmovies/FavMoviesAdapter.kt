package com.nugrahaa.moviecatalogue.ui.favmovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import kotlinx.android.synthetic.main.items_movies.view.*

class FavMoviesAdapter(
    private val callback: FavMoviesFragmentCallback)
    : PagedListAdapter<FavMovieEntity, FavMoviesAdapter.FavMoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavMovieEntity>() {
            override fun areItemsTheSame(
                oldItem: FavMovieEntity,
                newItem: FavMovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavMovieEntity,
                newItem: FavMovieEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMoviesAdapter.FavMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_movies, parent, false)
        return FavMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMoviesAdapter.FavMoviesViewHolder, position: Int) {
        val favMovie = getItem(position)
        if (favMovie != null) {
            holder.bind(favMovie)
        }
    }

    inner class FavMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: FavMovieEntity?) {
            with(itemView) {
                Log.d("HIHIHI" , movie?.title.toString())
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

//                itemView.setOnClickListener {
//                    callback.onClickGotoDetail(movie)
//                }
            }
        }
    }

}