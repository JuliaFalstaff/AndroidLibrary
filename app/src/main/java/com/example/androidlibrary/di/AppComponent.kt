package com.example.androidlibrary.di

import com.example.androidlibrary.di.users.UsersSubcomponent
import com.example.androidlibrary.mvp.MainActivity
import com.example.androidlibrary.mvp.presenter.DetailedRepoPresenter
import com.example.androidlibrary.mvp.presenter.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            CiceroneModule::class,
            DatabaseModule::class,
            ApiModule::class,
        ]
)
interface AppComponent {
    fun usersSubcomponent(): UsersSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(detailedRepoPresenter: DetailedRepoPresenter)
}