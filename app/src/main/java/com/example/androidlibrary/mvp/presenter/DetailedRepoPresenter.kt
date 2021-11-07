package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.view.IDetailedRepoView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailedRepoPresenter(
        val repository: GithubRepository?,
        val router: Router,
        val screen: IScreens,
) : MvpPresenter<IDetailedRepoView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository?.name.let { viewState.setRepoName(it) }
        repository?.forks_count.let { viewState.setCountOfForks(it) }
        repository?.language.let { viewState.setRepoLanguage(it) }
//        repository?.isPrivate.let { viewState.setRepoStatus(it) }
    }

    fun onBackCommandClick(): Boolean {
        router.backTo(screen.users())
        return true
    }
}