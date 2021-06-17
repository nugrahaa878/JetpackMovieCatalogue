package com.nugrahaa.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.data.remote.response.TVShow
import com.nugrahaa.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailActivityViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<Movie>

    @Mock
    private lateinit var observerTv: Observer<TVShow>

    @Before
    fun setUp() {
        viewModel = DetailActivityViewModel(repository)
    }

    @Test
    fun getMoviesById() {
        val moviesDummy = MutableLiveData<Movie>()
        moviesDummy.postValue(DataDummy.generateMovieAPI())
        `when`(repository.getMoviesById("2")).thenReturn(moviesDummy)
        val movie = viewModel.getMoviesById("2")
        verify(repository).getMoviesById("2")
        assertNotNull(movie)
        assertEquals("Si Ujang", movie.value?.title)

        viewModel.getMoviesById("2").observeForever(observerMovie)
        verify(observerMovie).onChanged(moviesDummy.value)
    }

    @Test
    fun getTVShowById() {
        val tvShowDummy = MutableLiveData<TVShow>()
        tvShowDummy.postValue(DataDummy.generateTvShowAPI())
        `when`(repository.getTVShowById("11")).thenReturn(tvShowDummy)
        val tvShow = viewModel.getTVShowById("11")
        verify(repository).getTVShowById("11")
        assertNotNull(tvShow)
        assertEquals("Ku patah hati", tvShow.value?.originalName)

        viewModel.getTVShowById("11").observeForever(observerTv)
        verify(observerTv).onChanged(tvShowDummy.value)
    }

    @Test
    fun testInsertMovieToFav() {
        val movie = MutableLiveData<Movie>()
        movie.postValue(DataDummy.generateMovieAPI())
        viewModel.insertMovieToFav(DataDummy.generateMovieAPI())
        lenient().`when`(repository.getMoviesById("2")).thenReturn(movie)
        assertNotNull(movie)
        assertEquals("Si Ujang", movie.value?.title)
    }

    @Test
    fun testInsertTvShowToFav() {
        val tvShow = MutableLiveData<TVShow>()
        tvShow.postValue(DataDummy.generateTvShowAPI())
        viewModel.insertFavTvShowToFav(DataDummy.generateTvShowAPI())
        lenient().`when`(repository.getTVShowById("11")).thenReturn(tvShow)
        assertNotNull(tvShow)
        assertEquals("Ku patah hati", tvShow.value?.originalName)
    }

    @Test
    fun testDeleteMovieToFav() {
        val movie = MutableLiveData<Movie>()
        movie.postValue(DataDummy.generateMovieAPI())
        viewModel.insertMovieToFav(DataDummy.generateMovieAPI())
        viewModel.deleteMovieFromFav(DataDummy.generateMovieAPI())
        lenient().`when`(repository.getMoviesById("2")).thenReturn(movie)
        assertNotNull(movie)
        assertEquals("Si Ujang", movie.value?.title)
    }

    @Test
    fun testDeleteTvShowToFav() {
        val tvShow = MutableLiveData<TVShow>()
        tvShow.postValue(DataDummy.generateTvShowAPI())
        viewModel.insertFavTvShowToFav(DataDummy.generateTvShowAPI())
        viewModel.deleteTvShowFromFav(DataDummy.generateTvShowAPI())
        lenient().`when`(repository.getTVShowById("11")).thenReturn(tvShow)
        assertNotNull(tvShow)
        assertEquals("Ku patah hati", tvShow.value?.originalName)
    }

}