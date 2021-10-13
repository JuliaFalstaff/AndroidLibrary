package com.example.androidlibrary.mvp

import android.view.View

interface IMainPresenter {
    fun attachView(mainView: IMainView)
    fun detachView()
}