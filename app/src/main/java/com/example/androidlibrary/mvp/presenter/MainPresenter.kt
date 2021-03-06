package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<IMainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.navigateTo(screen.users())
    }
}