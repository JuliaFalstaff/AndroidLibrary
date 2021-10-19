package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

