package com.tushar.moviz.state

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.tushar.moviz.bean.Movie

@Parcelize
data class HomeState private constructor(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false
): Parcelable {
    companion object {
        val initialState: HomeState
            get() = HomeState()
    }
}