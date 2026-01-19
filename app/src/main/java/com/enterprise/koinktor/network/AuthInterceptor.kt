package com.enterprise.koinktor.network

import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin

fun getAuthInterceptor(): ClientPlugin<Unit> {

    val authPlugin = createClientPlugin("AuthPlugin") {

        onRequest { request, _ ->
            request.headers.append("Authorization", "Bearer YOUR_TOKEN")
        }

        onResponse { response ->
            //if (response.status.value == 401) {
            //    // Handle token refresh
            //}
        }

    }

    return authPlugin

}
