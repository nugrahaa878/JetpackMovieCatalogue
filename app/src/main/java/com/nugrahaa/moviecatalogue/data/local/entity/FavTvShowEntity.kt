package com.nugrahaa.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favtvshowentities")
data class FavTvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "originalName")
    var originalName: String?,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String?,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double?,

    @ColumnInfo(name = "popularity")
    var popularity: Double?,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int?,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String?,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

)