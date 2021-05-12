package com.nugrahaa.moviecatalogue.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var id: Int,
    var title: String,
    var userscore: String,
    var date: String,
    var director: String,
    var genre: String,
    var description: String,
    var duration: String,
    var poster: Int,
) : Parcelable