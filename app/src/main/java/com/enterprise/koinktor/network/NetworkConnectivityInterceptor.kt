package com.enterprise.koinktor.network

import android.content.Context
import com.enterprise.koinktor.manager.InternetManager
import com.enterprise.koinktor.network.exception.NoInternetConnectionException
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin


fun getNetworkConnectivityInterceptor(context: Context): ClientPlugin<Unit> {

     val networkPlugin = createClientPlugin("NetworkPlugin") {

        onRequest { request, _ ->

            if(!InternetManager.isInternetAvailable(context = context)){

                throw NoInternetConnectionException(context = context)

            }

        }

        onResponse { response ->

        }

    }

    return networkPlugin

}



