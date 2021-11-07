package com.example.androidlibrary.di

import com.example.androidlibrary.mvp.model.githubrepositories.GitHubRepositoryImpl
import com.example.androidlibrary.mvp.model.githubrepositories.IGitHubRepositories
import com.example.androidlibrary.mvp.model.githubrepositories.IRoomGitHubRepositoryCache
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubRepositoryModule {

    @Singleton
    @Provides
    fun getRepositoriesList(
        api: RetrofitAPI,
        networkStatus: INetworkStatus,
        db: IRoomGitHubRepositoryCache
    ): IGitHubRepositories = GitHubRepositoryImpl(api, networkStatus, db)
}
