package com.enterprise.koinktor.remotedatasource

import io.ktor.client.statement.HttpResponse

interface PostAPI {

    suspend fun getPosts(): HttpResponse


}