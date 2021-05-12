package com.nugrahaa.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.remote.response.Movie
import com.nugrahaa.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<ArrayList<Movie?>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        val moviesDummy = MutableLiveData<ArrayList<Movie?>>()
        moviesDummy.postValue(DataDummy.generateDummyMoviesAPI())
        `when`(repository.getAllMovies()).thenReturn(moviesDummy)
        val movies = viewModel.getMovies()
        verify(repository).getAllMovies()
        assertNotNull(movies)
        assertEquals(3, movies.value?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(moviesDummy.value)
    }

}