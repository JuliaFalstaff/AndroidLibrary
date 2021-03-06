package com.example.androidlibrary.di.users

import com.example.androidlibrary.App
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.model.user.GithubUsersRepoImpl
import com.example.androidlibrary.mvp.model.user.IGitHubUsersRepo
import com.example.androidlibrary.mvp.model.user.IRoomGitHubUsersCache
import com.example.androidlibrary.mvp.model.user.RoomGithubUsersCacheImpl
import com.example.androidlibrary.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class UsersModule {

    @Provides
    fun usersCache(database: AppDataBase): IRoomGitHubUsersCache =
            RoomGithubUsersCacheImpl(database)

    @UsersScope
    @Provides
    fun getUsersList(
            api: RetrofitAPI,
            networkStatus: INetworkStatus,
            db: IRoomGitHubUsersCache
    ): IGitHubUsersRepo = GithubUsersRepoImpl(api, networkStatus, db)

    @UsersScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}