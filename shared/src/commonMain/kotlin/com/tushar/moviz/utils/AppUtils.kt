package com.tushar.moviz.utils

object AppUtils {
    fun getImageUrl(path: String?): String? {
        if (path == null) return null
        return "https://image.tmdb.org/t/p/w500/$path"
    }
}