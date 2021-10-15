package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.model.CountersModel
import moxy.MvpPresenter

class MainPresenter(val model: CountersModel) : MvpPresenter<IMainView>() {

    fun counterClick(position: Int) {
        when (position) {
            CounterPosition.FIRST.position -> {
                val nextValue = model.next(CounterPosition.FIRST.position)
                viewState.setButtonFirstText(nextValue.toString())
            }
            CounterPosition.SECOND.position -> {
                val nextValue = model.next(CounterPosition.SECOND.position)
                viewState.setButtonSecondText(nextValue.toString())
            }
            CounterPosition.THIRD.position -> {
                val nextValue = model.next(CounterPosition.THIRD.position)
                viewState.setButtonThirdText(nextValue.toString())
            }
        }
    }
}

enum class CounterPosition(val position: Int) {
    FIRST(0),
    SECOND(1),
    THIRD(2)
}