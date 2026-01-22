package com.enterprise.koinktor.remotedatasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class PostAPIImplemenation(private val client: HttpClient): PostAPI {

    override suspend fun getPosts(): HttpResponse {
        return client
            .get("${PostAPIConstants.BASE_URL}/posts")
    }

}