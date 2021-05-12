package com.nugrahaa.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favmovieentities")
data class FavMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String?,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String?,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double?,

    @ColumnInfo(name = "popularity")
    var popularity: Double?,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int?,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "video")
    var video: Boolean?,

    @ColumnInfo(name = "adult")
    var adult: Boolean?,
)
