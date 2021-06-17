package com.nugrahaa.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nugrahaa.moviecatalogue.data.local.LocalDataSource
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity
import com.nugrahaa.moviecatalogue.data.remote.RemoteDataSource
import com.nugrahaa.moviecatalogue.utils.DataDummy
import com.nugrahaa.moviecatalogue.utils.LiveDataTestUtil
import com.nugrahaa.moviecatalogue.utils.PagedListUtil
import io.reactivex.rxjava3.core.Flowable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeRepository

    private val moviesResponse = DataDummy.generateDummyMoviesAPI()
    private val tvShowResponse = DataDummy.generateDummyTvShowAPI()

    @Mock
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)

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

    @Test
    fun getFavMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavMovieEntity>
        `when`(local.getAllFavMovie()).thenReturn(dataSourceFactory)
        repository.getFavMovie()

        val movieEntities = PagedListUtil.mockPagedList(DataDummy.generateDummyMoviesAPI())
        verify(local).getAllFavMovie()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getFavTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavTvShowEntity>
        `when`(local.getAllFavTvShow()).thenReturn(dataSourceFactory)
        repository.getFavTvShow()

        val tvShowEntities = PagedListUtil.mockPagedList(DataDummy.generateDummyTvShowAPI())
        verify(local).getAllFavTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun addMovieToFavorite() = runBlocking {
        val movie = moviesResponse[0]
        val favMovie = FavMovieEntity(movie?.id, movie?.title, movie?.overview, movie?.originalTitle, movie?.originalLanguage, movie?.voteAverage, movie?.popularity, movie?.voteCount, movie?.releaseDate, movie?.backdropPath, movie?.posterPath, movie?.video, movie?.adult)
        movie?.let { repository.addMovieToFavorite(it) }
        verify(local).insertFavMovie(favMovie)
    }

    @Test
    fun addTvShowToFavorite() = runBlocking {
        val tvShow = tvShowResponse[0]
        val favTvShow = FavTvShowEntity(tvShow?.id, tvShow?.name, tvShow?.overview, tvShow?.originalName, tvShow?.originalLanguage, tvShow?.voteAverage, tvShow?.popularity, tvShow?.voteCount, tvShow?.firstAirDate, tvShow?.backdropPath, tvShow?.posterPath)
        tvShow?.let { repository.addTVShowToFavorite(it) }
        verify(local).insertFavTvShow(favTvShow)
    }

    @Test
    fun deleteMovieFromFavorite() = runBlocking {
        val movie = moviesResponse[0]
        val favMovie = FavMovieEntity(movie?.id, movie?.title, movie?.overview, movie?.originalTitle, movie?.originalLanguage, movie?.voteAverage, movie?.popularity, movie?.voteCount, movie?.releaseDate, movie?.backdropPath, movie?.posterPath, movie?.video, movie?.adult)
        movie?.let { repository.deleteMovieFromFavorite(it) }
        verify(local).deleteMovieFromFavorite(favMovie)
    }

    @Test
    fun deleteTVShowFromFavorite() = runBlocking {
        val tvShow = tvShowResponse[0]
        val favTvShow = FavTvShowEntity(tvShow?.id, tvShow?.name, tvShow?.overview, tvShow?.originalName, tvShow?.originalLanguage, tvShow?.voteAverage, tvShow?.popularity, tvShow?.voteCount, tvShow?.firstAirDate, tvShow?.backdropPath, tvShow?.posterPath)
        tvShow?.let { repository.deleteTVShowFromFavorite(it) }
        verify(local).deleteTvShowFromFavorite(favTvShow)
    }

}