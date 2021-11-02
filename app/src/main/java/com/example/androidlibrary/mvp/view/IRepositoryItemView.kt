package com.example.androidlibrary.mvp.view

interface IRepositoryItemView : IItemView {
    fun setRepo(url: String?)
    fun setNameRepo(name: String?)
}