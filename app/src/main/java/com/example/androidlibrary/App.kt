package com.example.androidlibrary

import android.app.Application
import androidx.room.Room
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
        private lateinit var db: AppDataBase
        const val DB_NAME = "dtabase.db"
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    fun getDB(): AppDataBase = db

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(instance, AppDataBase::class.java, DB_NAME).build()
    }
}