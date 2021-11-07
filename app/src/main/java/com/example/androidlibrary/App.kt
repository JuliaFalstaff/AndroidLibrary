package com.example.androidlibrary

import android.app.Application
import androidx.room.Room
import com.example.androidlibrary.di.AppComponent
import com.example.androidlibrary.di.AppModule
import com.example.androidlibrary.di.DaggerAppComponent
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.facebook.stetho.Stetho
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        Stetho.initializeWithDefaults(this)
    }
}