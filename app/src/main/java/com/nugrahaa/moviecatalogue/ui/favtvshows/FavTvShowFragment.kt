package com.nugrahaa.moviecatalogue.ui.favtvshows

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
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.ui.detail.DetailActivity
import com.nugrahaa.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_tv_show.*

class FavTvShowFragment : Fragment(), FavTvShowFragmentCallback {

    private lateinit var viewModel: FavTvShowViewModel
    private lateinit var rvTvShow: RecyclerView
    private lateinit var listFavTvShowAdapter: FavTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fav_tvshow_progress.visibility = View.VISIBLE
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            ) [FavTvShowViewModel::class.java]

            viewModel.getFavTvShow()
            attachObserver()
        }
    }

    private fun attachObserver() {
        viewModel.getFavTvShow().observe(viewLifecycleOwner, Observer {
            fav_tvshow_progress.visibility = View.GONE
            listFavTvShowAdapter.submitList(it)
        })
        rvTvShow = fav_rv_tvshow
        rvTvShow.layoutManager = LinearLayoutManager(context)
        listFavTvShowAdapter = FavTvShowAdapter(this)
        rvTvShow.adapter = listFavTvShowAdapter
    }

    override fun onClickGoToDetail(tvShow: FavTvShowEntity?) {
        val mIntent = Intent(context, DetailActivity::class.java)
        mIntent.putExtra("TYPE", "tvshow")
        mIntent.putExtra("ID", tvShow?.id)
        startActivity(mIntent)
    }
}