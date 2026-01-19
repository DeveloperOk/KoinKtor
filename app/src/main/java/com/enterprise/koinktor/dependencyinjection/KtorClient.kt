package com.enterprise.koinktor.dependencyinjection

import android.content.Context
import com.enterprise.koinktor.network.getAuthInterceptor
import com.enterprise.koinktor.network.getNetworkConnectivityInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideHttpClient(context: Context): HttpClient =
    HttpClient(Android) {

        install(getNetworkConnectivityInterceptor(context = context))

        install(getAuthInterceptor())

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }

    }

