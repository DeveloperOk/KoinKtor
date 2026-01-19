package com.enterprise.koinktor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enterprise.koinktor.model.Post
import com.enterprise.koinktor.network.exception.NoInternetConnectionException
import com.enterprise.koinktor.repository.PostRepository
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _isNoInternetConnectionDialogVisible = MutableStateFlow(false)
    val isNoInternetConnectionDialogVisible: StateFlow<Boolean> = _isNoInternetConnectionDialogVisible

    fun updateNoInternetConnectionDialogVisible(newValue: Boolean){

        _isNoInternetConnectionDialogVisible.update{ newValue }

    }

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.update {true}
            try {

                val httpResponse = repository.getPosts()

                _posts.update { httpResponse.body() }

            } catch (e: Exception) {
                e.printStackTrace()

                if(e is NoInternetConnectionException){

                    _isNoInternetConnectionDialogVisible.update { true }

                }

            } finally {
                _loading.update { false }
            }
        }
    }
}