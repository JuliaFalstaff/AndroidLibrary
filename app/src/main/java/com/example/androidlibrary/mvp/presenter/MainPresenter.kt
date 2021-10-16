package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router : Router, val screen: IScreens) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}