package com.nugrahaa.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.nugrahaa.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val favMoviesFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        replaceFragment(homeFragment, "Home")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment, "Home")
                R.id.ic_favorite -> replaceFragment(favMoviesFragment, "Favorite")
            }
            true
        }

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportActionBar?.title = title
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}