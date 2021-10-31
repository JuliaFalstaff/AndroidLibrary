package com.example.androidlibrary.mvp.view.avatar

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
}