package com.example.androidlibrary.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IDetailedRepoView : MvpView {
    fun setCountOfForks(count: Int?)
    fun setRepoName(name: String?)
    fun setRepoStatus(isPrivate: Boolean?)
    fun setRepoLanguage(language: String?)
}