package com.example.androidlibrary.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView: MvpView {
    fun setButtonFirstText(text: String)
    fun setButtonSecondText(text: String)
    fun setButtonThirdText(text: String)
}