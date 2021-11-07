package com.example.androidlibrary.di

import com.example.androidlibrary.App
import dagger.Module
import dagger.Provides

@Module
class AppModule (val app: App) {

    @Provides
    fun app(): App {
        return app
    }


}