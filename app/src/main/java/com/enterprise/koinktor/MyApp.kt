package com.enterprise.koinktor

import android.app.Application
import com.enterprise.koinktor.dependencyinjection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

    }

}