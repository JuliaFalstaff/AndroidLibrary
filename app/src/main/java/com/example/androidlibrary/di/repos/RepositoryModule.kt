package com.example.androidlibrary.di.repos

import com.example.androidlibrary.App
import com.example.androidlibrary.mvp.model.githubrepositories.GitHubRepositoryImpl
import com.example.androidlibrary.mvp.model.githubrepositories.IGitHubRepositories
import com.example.androidlibrary.mvp.model.githubrepositories.IRoomGitHubRepositoryCache
import com.example.androidlibrary.mvp.model.githubrepositories.RoomGitHubRepositoryCacheImpl
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: AppDataBase): IRoomGitHubRepositoryCache =
            RoomGitHubRepositoryCacheImpl(database)

    @RepositoryScope
    @Provides
    fun getRepositoriesList(
            api: RetrofitAPI,
            networkStatus: INetworkStatus,
            db: IRoomGitHubRepositoryCache
    ): IGitHubRepositories = GitHubRepositoryImpl(api, networkStatus, db)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}