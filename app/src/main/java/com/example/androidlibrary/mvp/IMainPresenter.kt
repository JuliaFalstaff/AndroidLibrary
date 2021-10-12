package com.example.androidlibrary.mvp

import android.view.View

interface IMainPresenter {
    fun attachView(view: View)
    fun detachView()
}