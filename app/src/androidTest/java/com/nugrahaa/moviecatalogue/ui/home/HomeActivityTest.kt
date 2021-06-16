package com.nugrahaa.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nugrahaa.moviecatalogue.R
import com.nugrahaa.moviecatalogue.utils.DataDummy
import com.nugrahaa.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.SECONDS)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)

    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovies() {
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
    }
    
    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun addOrDeleteTVShowToFavorite() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.floating_favorite)).perform(click())
    }

    @Test
    fun addOrDeleteMovieToFavorite() {
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.floating_favorite)).perform(click())
    }

    @Test
    fun addOrDeleteTvShowToFavorite() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.floating_favorite)).perform(click())
    }

    @Test
    fun loadMovieFavorite() {
        onView(withId(R.id.ic_favorite)).perform(click())
        onView(withId(R.id.fav_rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadTvShowFavorite() {
        onView(withId(R.id.ic_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.fav_rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

}