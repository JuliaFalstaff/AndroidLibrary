package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.view.IDetailedRepoView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedRepoPresenter(val repository: GithubRepository?) : MvpPresenter<IDetailedRepoView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository?.name.let { viewState.setRepoName(it) }
        repository?.forks_count.let { viewState.setCountOfForks(it) }
        repository?.language.let { viewState.setRepoLanguage(it) }
    }

    fun onBackCommandClick(): Boolean {
        router.exit()
        return true
    }
}