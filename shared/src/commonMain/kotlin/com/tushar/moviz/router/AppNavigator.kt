package com.tushar.moviz.router

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.tushar.moviz.bean.Movie

@Parcelize
sealed class AppNavigator: Parcelable {
    object MoviesList : AppNavigator()
    data class MovieDetail(val detail: Movie) : AppNavigator()
}