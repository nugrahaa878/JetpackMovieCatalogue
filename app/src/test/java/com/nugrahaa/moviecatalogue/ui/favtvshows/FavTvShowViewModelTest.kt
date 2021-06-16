package com.nugrahaa.moviecatalogue.ui.favtvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nugrahaa.moviecatalogue.data.Repository
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.junit.Assert.*
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvShowViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavTvShowViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var pagedList: PagedList<FavTvShowEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<FavTvShowEntity>>

    @Before
    fun setUp() {
        viewModel = FavTvShowViewModel(repository)
    }

    @Test
    fun testGetFavTvShow() {
        val dummyFavTvShows = pagedList
        `when`(dummyFavTvShows.size).thenReturn(2)
        val tvshows = MutableLiveData<PagedList<FavTvShowEntity>>()
        tvshows.value = dummyFavTvShows

        `when`(repository.getFavTvShow()).thenReturn(tvshows)
        val favTvShowEntities = viewModel.getFavTvShow().value
        verify(repository).getFavTvShow()
        assertNotNull(favTvShowEntities)
        assertEquals(2, favTvShowEntities?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyFavTvShows)
    }
}