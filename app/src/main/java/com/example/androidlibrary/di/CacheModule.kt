package com.example.androidlibrary.di

import androidx.room.Room
import com.example.androidlibrary.App
import com.example.androidlibrary.mvp.model.githubrepositories.IRoomGitHubRepositoryCache
import com.example.androidlibrary.mvp.model.githubrepositories.RoomGitHubRepositoryCacheImpl
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.model.user.IRoomGitHubUsersCache
import com.example.androidlibrary.mvp.model.user.RoomGithubUsersCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): AppDataBase = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "database.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun usersCache(database: AppDataBase): IRoomGitHubUsersCache =
        RoomGithubUsersCacheImpl(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: AppDataBase): IRoomGitHubRepositoryCache =
        RoomGitHubRepositoryCacheImpl(database)
}