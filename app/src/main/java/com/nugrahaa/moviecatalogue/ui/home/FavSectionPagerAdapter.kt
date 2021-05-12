package com.nugrahaa.moviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.ui.favmovies.FavMoviesFragment
import com.nugrahaa.moviecatalogue.ui.favtvshows.FavTvShowFragment

class FavSectionPagerAdapter(private val mContext: Context?, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMoviesFragment()
            1 -> FavTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext?.resources?.getString(TAB_TITLES[position])

}