package com.example.androidlibrary.mvp.presenter


import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.view.IDetailedUserView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailedUsersPresenter(
    val user: GithubUser?,
    val router: Router,
    val screen: IScreens
) :
    MvpPresenter<IDetailedUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.login?.let { viewState.setUserLogin(it) }
    }

    fun onBackCommandClick(): Boolean {
        router.backTo(screen.users())
        return true
    }
}