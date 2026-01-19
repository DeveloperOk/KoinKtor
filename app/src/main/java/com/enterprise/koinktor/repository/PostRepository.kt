package com.enterprise.koinktor.repository

import com.enterprise.koinktor.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class PostRepository(
    private val client: HttpClient
) {

    suspend fun getPosts(): HttpResponse {
        return client
            .get("https://jsonplaceholder.typicode.com/posts")
    }

}