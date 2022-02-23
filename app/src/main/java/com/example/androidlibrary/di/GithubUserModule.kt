package com.example.androidlibrary.di

import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.model.user.GithubUsersRepoImpl
import com.example.androidlibrary.mvp.model.user.IGitHubUsersRepo
import com.example.androidlibrary.mvp.model.user.IRoomGitHubUsersCache
import com.example.androidlibrary.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubUserModule {

    @Singleton
    @Provides
    fun getUsersList(
        api: RetrofitAPI,
        networkStatus: INetworkStatus,
        db: IRoomGitHubUsersCache
    ): IGitHubUsersRepo = GithubUsersRepoImpl(api, networkStatus, db)
}

