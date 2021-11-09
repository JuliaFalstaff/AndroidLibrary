package com.example.androidlibrary.di.users

import com.example.androidlibrary.di.repos.RepositorySubcomponent
import com.example.androidlibrary.mvp.presenter.UsersPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
        modules = [
            UsersModule::class
        ]
)
interface UsersSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
}