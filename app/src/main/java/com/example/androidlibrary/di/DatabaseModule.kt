package com.example.androidlibrary.di

import androidx.room.Room
import com.example.androidlibrary.App
import com.example.androidlibrary.mvp.model.room.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): AppDataBase = Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "database.db"
    )
            .fallbackToDestructiveMigration()
            .build()
}