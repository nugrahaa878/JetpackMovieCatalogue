package com.nugrahaa.moviecatalogue.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.ui.home.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionPagerAdapter = SectionPagerAdapter(context, childFragmentManager)
        view_pager_home.adapter = sectionPagerAdapter
        tabs_home.setupWithViewPager(view_pager_home)
    }

}