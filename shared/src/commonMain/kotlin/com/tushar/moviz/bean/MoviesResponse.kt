package com.tushar.moviz.bean

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MoviesResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<Movie>?
): Parcelable

@Serializable
@Parcelize
data class Movie(
    @SerialName("id")
    val id: String?,
    @SerialName("poster_path")
    val backdropPath: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("overview")
    val overview: String?
): Parcelable