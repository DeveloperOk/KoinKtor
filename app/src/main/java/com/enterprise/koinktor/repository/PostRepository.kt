package com.enterprise.koinktor.repository

import com.enterprise.koinktor.model.Post
import com.enterprise.koinktor.remotedatasource.PostAPI
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class PostRepository(
    private val postAPI: PostAPI
) {

    suspend fun getPosts(): HttpResponse {
        return postAPI.getPosts()
    }

}