package com.tushar.moviz.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import com.tushar.moviz.BuildKonfig

@OptIn(ExperimentalSerializationApi::class)
fun getHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })
        }

        defaultRequest {
            url {
                host = "api.themoviedb.org"
                protocol = URLProtocol.HTTPS
                parameters.append("api_key", BuildKonfig.API_KEY)
            }
        }
    }
}
