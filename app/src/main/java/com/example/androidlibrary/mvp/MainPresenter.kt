package com.example.androidlibrary.mvp

import com.example.androidlibrary.R

class MainPresenter(val view: IMainView) {
    val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun counterClick(id: Int) {
        when(id) {
            R.id.buttonCounterFirst -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.buttonCounterSecond -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.buttonCounterThird -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }

        }
    }

}