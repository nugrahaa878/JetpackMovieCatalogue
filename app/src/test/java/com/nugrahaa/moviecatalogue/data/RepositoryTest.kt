package com.nugrahaa.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nugrahaa.moviecatalogue.data.local.LocalDataSource
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource
import com.nugrahaa.moviecatalogue.utils.DataDummy
import com.nugrahaa.moviecatalogue.utils.LiveDataTestUtil
import io.reactivex.rxjava3.core.Flowable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeRepository

    @Mock
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)

    @Before
    fun setUp() {
        repository = FakeRepository(remote, local)
    }

    @Test
    fun getAllMovies() {
        val moviesResults = DataDummy.generateResponseMovieDummyAPI()
        `when`(remote.getMovies()).thenReturn(Flowable.just(moviesResults))
        val moviesResponse = LiveDataTestUtil.getValue(repository.getAllMovies())
        verify(remote).getMovies()
        assertNotNull(moviesResponse)
        assertEquals(3, moviesResponse.size)
    }

    @Test
    fun getAllTVShow() {
        val tvShowResults = DataDummy.generateResponseTVShowDummyAPI()
        `when`(remote.getTvShow()).thenReturn(Flowable.just(tvShowResults))
        val tvShowResponse = LiveDataTestUtil.getValue(repository.getAllTvShow())
        verify(remote).getTvShow()
        assertNotNull(tvShowResponse)
        assertEquals(2, tvShowResponse.size)
    }

    @Test
    fun getMoviesById() {
        val movie = DataDummy.generateMovieAPI()
        `when`(remote.getMoviesById("2")).thenReturn(Flowable.just(movie))
        val movieResponse = LiveDataTestUtil.getValue(repository.getMoviesById("2"))
        verify(remote).getMoviesById("2")
        assertNotNull(movieResponse)
        assertEquals("Si Ujang", movieResponse.title)
    }

    @Test
    fun getTVShowById() {
        val tvShow = DataDummy.generateTvShowAPI()
        `when`(remote.getTVShowById("3")).thenReturn(Flowable.just(tvShow))
        val tvShowResponse = LiveDataTestUtil.getValue(repository.getTVShowById("3"))
        verify(remote).getTVShowById("3")
        assertNotNull(tvShowResponse)
        assertEquals("Ku patah hati", tvShowResponse.originalName)
    }

}