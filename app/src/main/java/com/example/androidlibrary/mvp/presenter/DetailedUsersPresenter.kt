package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.view.IDetailedUserView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailedUsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val screen: IScreens
) :
    MvpPresenter<IDetailedUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun setUserLogin(position: Int): String {
        return usersRepo.getUsers()[position].login
    }

    fun onBackCommandClick(): Boolean {
        router.backTo(screen.users())
        return true
    }

    fun getAvatar(position: Int) = usersRepo.getUsers()[position].loginImage.toInt()
}