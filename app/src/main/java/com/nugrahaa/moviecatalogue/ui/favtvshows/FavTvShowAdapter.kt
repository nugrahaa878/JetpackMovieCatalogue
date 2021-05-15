package com.nugrahaa.moviecatalogue.ui.favtvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import kotlinx.android.synthetic.main.items_movies.view.*
import kotlinx.android.synthetic.main.items_tvshow.view.*

class FavTvShowAdapter(
    private val callback: FavTvShowFragmentCallback)
    : PagedListAdapter<FavTvShowEntity, FavTvShowAdapter.FavTvViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavTvShowEntity>() {
            override fun areItemsTheSame(
                oldItem: FavTvShowEntity,
                newItem: FavTvShowEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavTvShowEntity,
                newItem: FavTvShowEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavTvShowAdapter.FavTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_tvshow, parent, false)
        return FavTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTvShowAdapter.FavTvViewHolder, position: Int) {
        val favTvShow = getItem(position)
        if (favTvShow != null) {
            holder.bind(favTvShow)
        }
    }

    inner class FavTvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: FavTvShowEntity?) {
            with (itemView) {
                tv_tvshow_title.text = tvShow?.originalName
                tv_tvshow_date.text = tvShow?.firstAirDate
                tv_tvshow_description.text = tvShow?.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + tvShow?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster_tvshow)
            }
        }
    }

}