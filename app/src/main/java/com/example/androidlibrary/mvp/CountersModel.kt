package com.example.androidlibrary.mvp

class CountersModel {
    private val counters = mutableListOf(0, 0, 0)

    fun next(index: Int): Int {
        return ++counters[index]
    }
}
