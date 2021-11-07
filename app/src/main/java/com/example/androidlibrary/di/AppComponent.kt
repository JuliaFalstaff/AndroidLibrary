package com.example.androidlibrary.di

import com.example.androidlibrary.mvp.MainActivity
import com.example.androidlibrary.mvp.presenter.MainPresenter
import com.example.androidlibrary.mvp.presenter.UsersPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        GithubUserModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
}