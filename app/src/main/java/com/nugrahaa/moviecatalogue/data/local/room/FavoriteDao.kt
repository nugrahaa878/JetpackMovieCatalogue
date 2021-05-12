package com.nugrahaa.moviecatalogue.data.local.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.nugrahaa.moviecatalogue.data.local.entity.FavMovieEntity
import com.nugrahaa.moviecatalogue.data.local.entity.FavTvShowEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavMovie(favMovies: FavMovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(favTShow: FavTvShowEntity)

    @Query("SELECT * FROM favmovieentities")
    fun getFavMovie(): DataSource.Factory<Int, FavMovieEntity>

    @Query("SELECT * FROM favtvshowentities")
    fun getFavTvShow(): DataSource.Factory<Int, FavTvShowEntity>

    @Query("SELECT * FROM favmovieentities WHERE title = :title")
    fun getMovieByTitle(title: String?): LiveData<List<FavMovieEntity>>

    @Query("SELECT * FROM favtvshowentities WHERE name = :name")
    fun getTvShowByName(name: String?): LiveData<List<FavTvShowEntity>>

    @Delete
    fun delete(favMovie: FavMovieEntity)

    @Query("DELETE FROM favmovieentities WHERE title = :title")
    fun deleteFavMovieByTitle(title: String?)

    @Query("DELETE FROM favtvshowentities WHERE name = :name")
    fun deleteFavTvShowByName(name: String?)

}