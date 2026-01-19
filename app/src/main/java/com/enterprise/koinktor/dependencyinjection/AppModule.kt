package com.enterprise.koinktor.dependencyinjection

import com.enterprise.koinktor.repository.PostRepository
import com.enterprise.koinktor.viewmodel.PostViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    // Network
    single { provideHttpClient() }

    // Repository
    single { PostRepository(get()) }

    // ViewModel
    viewModel { PostViewModel(get()) }
}