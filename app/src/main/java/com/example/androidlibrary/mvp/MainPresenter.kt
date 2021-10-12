package com.example.androidlibrary.mvp

import android.view.View

class MainPresenter(private val view: IMainView) : IMainPresenter {
    private val model = CountersModel()

    fun counterClick(position: Int) {
        when (position) {
            CounterPosition.FIRST.position -> {
                val nextValue = model.next(CounterPosition.FIRST.position)
                view.setButtonFirstText(nextValue.toString())
            }
            CounterPosition.SECOND.position -> {
                val nextValue = model.next(CounterPosition.SECOND.position)
                view.setButtonSecondText(nextValue.toString())
            }
            CounterPosition.THIRD.position -> {
                val nextValue = model.next(CounterPosition.THIRD.position)
                view.setButtonThirdText(nextValue.toString())
            }
        }
    }

    override fun attachView(view: View) {
        TODO("Not yet implemented")
    }

    override fun detachView() {
        //FIXME
        this.view = null
    }
}

enum class CounterPosition(val position: Int) {
    FIRST(0),
    SECOND(1),
    THIRD(2)
}