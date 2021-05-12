package com.nugrahaa.moviecatalogue.ui.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import kotlinx.android.synthetic.main.items_tvshow.view.*

class TvShowsAdapter(
    private val listTvShow: ArrayList<TVShow?>,
    private val callback: TvShowsFragmentCallback
) :
    RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowsAdapter.TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_tvshow, parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.TvShowsViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TVShow?) {
            with(itemView) {
                tv_tvshow_title.text = tvShow?.originalName
                tv_tvshow_description.text = tvShow?.overview
                tv_tvshow_date.text = tvShow?.firstAirDate
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + tvShow?.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_poster_tvshow)

                itemView.setOnClickListener {
                    callback.onClickGotoDetail(tvShow)
                }

            }
        }
    }

}