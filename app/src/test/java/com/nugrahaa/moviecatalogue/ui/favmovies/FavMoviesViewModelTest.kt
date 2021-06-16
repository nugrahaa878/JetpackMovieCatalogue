package com.nugrahaa.moviecatalogue.ui.favmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
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
class FavMoviesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavMoviesViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var pagedList: PagedList<FavMovieEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<FavMovieEntity>>

    @Before
    fun setUp() {
        viewModel = FavMoviesViewModel(repository)
    }

    @Test
    fun testGetFavMovies() {
        val dummyFavMovie = pagedList
        `when`(dummyFavMovie.size).thenReturn(2)
        val favMovie = MutableLiveData<PagedList<FavMovieEntity>>()
        favMovie.value = dummyFavMovie

        `when`(repository.getFavMovie()).thenReturn(favMovie)
        val favMovieEntities = viewModel.getFavMovies().value
        verify(repository).getFavMovie()
        assertNotNull(favMovieEntities)
        assertEquals(2, favMovieEntities?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavMovie)
    }
}