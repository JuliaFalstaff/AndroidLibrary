package com.example.androidlibrary

import android.app.Application
import com.example.androidlibrary.di.*
import com.example.androidlibrary.di.repos.IRepositoryScopeContainer
import com.example.androidlibrary.di.repos.RepositorySubcomponent
import com.example.androidlibrary.di.users.IUserScopeContainer
import com.example.androidlibrary.di.users.UsersSubcomponent
import com.facebook.stetho.Stetho

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    var usersSubcomponent: UsersSubcomponent? = null
    var repositorySubcomponent: RepositorySubcomponent? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        Stetho.initializeWithDefaults(this)
    }

    fun initUserSubComponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initReposSubComponent() = usersSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseUsersScope() {
        usersSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }
}