package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.model.CountersModel

class MainPresenter(private var view: IMainView?) : IMainPresenter {
    private val model = CountersModel()

    fun counterClick(position: Int) {
        when (position) {
            CounterPosition.FIRST.position -> {
                val nextValue = model.next(CounterPosition.FIRST.position)
                view?.setButtonFirstText(nextValue.toString())
            }
            CounterPosition.SECOND.position -> {
                val nextValue = model.next(CounterPosition.SECOND.position)
                view?.setButtonSecondText(nextValue.toString())
            }
            CounterPosition.THIRD.position -> {
                val nextValue = model.next(CounterPosition.THIRD.position)
                view?.setButtonThirdText(nextValue.toString())
            }
        }
    }

    override fun attachView(mainView: IMainView) {
        if (view != mainView) {
            view = mainView
        }
    }

    override fun detachView() {
        view = null
    }
}

enum class CounterPosition(val position: Int) {
    FIRST(0),
    SECOND(1),
    THIRD(2)
}