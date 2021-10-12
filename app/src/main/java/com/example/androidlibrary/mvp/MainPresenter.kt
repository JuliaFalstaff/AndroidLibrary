package com.example.androidlibrary.mvp

class MainPresenter(private val view: IMainView) {
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
}

enum class CounterPosition(val position: Int) {
    FIRST(0),
    SECOND(1),
    THIRD(2)
}