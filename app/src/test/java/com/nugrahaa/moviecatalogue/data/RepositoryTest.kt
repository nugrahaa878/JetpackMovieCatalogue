package com.nugrahaa.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource
import com.nugrahaa.moviecatalogue.utils.DataDummy
import com.nugrahaa.moviecatalogue.utils.LiveDataTestUtil
import io.reactivex.rxjava3.core.Flowable
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
class RepositoryTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var fakeRepository: FakeRepository

    @Mock
    private lateinit var remote: RemoteDataSource

    @Before
    fun setUp() {
        fakeRepository = FakeRepository(remote)
    }

    @Test
    fun getAllMovies() {
        val moviesResults = DataDummy.generateResponseMovieDummyAPI()
        `when`(remote.getMovies()).thenReturn(Flowable.just(moviesResults))
        val moviesResponse = LiveDataTestUtil.getValue(fakeRepository.getAllMovies())
        verify(remote).getMovies()
        assertNotNull(moviesResponse)
        assertEquals(3, moviesResponse.size)
    }

    @Test
    fun getAllTVShow() {
        val tvShowResults = DataDummy.generateResponseTVShowDummyAPI()
        `when`(remote.getTvShow()).thenReturn(Flowable.just(tvShowResults))
        val tvShowResponse = LiveDataTestUtil.getValue(fakeRepository.getAllTvShow())
        verify(remote).getTvShow()
        assertNotNull(tvShowResponse)
        assertEquals(2, tvShowResponse.size)
    }

    @Test
    fun getMoviesById() {
        val movie = DataDummy.generateMovieAPI()
        `when`(remote.getMoviesById("2")).thenReturn(Flowable.just(movie))
        val movieResponse = LiveDataTestUtil.getValue(fakeRepository.getMoviesById("2"))
        verify(remote).getMoviesById("2")
        assertNotNull(movieResponse)
        assertEquals("Si Ujang", movieResponse.title)
    }

    @Test
    fun getTVShowById() {
        val tvShow = DataDummy.generateTvShowAPI()
        `when`(remote.getTVShowById("3")).thenReturn(Flowable.just(tvShow))
        val tvShowResponse = LiveDataTestUtil.getValue(fakeRepository.getTVShowById("3"))
        verify(remote).getTVShowById("3")
        assertNotNull(tvShowResponse)
        assertEquals("Ku patah hati", tvShowResponse.originalName)
    }
}