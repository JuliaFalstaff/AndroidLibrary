package com.example.androidlibrary.mvp.view

import com.example.androidlibrary.mvp.model.data.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IDetailedUserView : MvpView {
    fun setUserLogin(login: String?)
    fun setLoadAvatar(avatarUser: String?)
}