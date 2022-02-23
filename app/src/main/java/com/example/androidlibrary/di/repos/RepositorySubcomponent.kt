package com.example.androidlibrary.di.repos

import com.example.androidlibrary.mvp.presenter.DetailedRepoPresenter
import com.example.androidlibrary.mvp.presenter.RepositoryPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
        modules = [
            RepositoryModule::class
        ]
)
interface RepositorySubcomponent {
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(detailedRepoPresenter: DetailedRepoPresenter)
}