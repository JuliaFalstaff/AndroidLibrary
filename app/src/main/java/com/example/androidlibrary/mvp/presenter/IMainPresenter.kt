package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IMainView

interface IMainPresenter {
    fun attachView(mainView: IMainView)
    fun detachView()
}