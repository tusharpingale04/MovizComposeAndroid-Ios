package com.tushar.moviz.api

import com.tushar.moviz.bean.MoviesResponse
import com.tushar.moviz.utils.get
import io.ktor.client.*
import io.ktor.http.*

class MovizWebService(private val client: HttpClient) {
    suspend fun getTopMovies(): Result<MoviesResponse> = client
        .get {
            url {
                path("3/trending/all/day")
            }
        }
}