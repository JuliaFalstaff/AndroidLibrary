package com.example.androidlibrary.mvp.presenter

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class Creation {
    fun exec() {
        Consumer(Producer()).exec()
    }

    //Observable
    class Producer {
        fun fromIterable(): Observable<String> {
            return Observable.fromIterable(listOf("1", "2", "3"))
        }
    }

    //Observer
    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(t: String?) {
                println("onNext: $t")
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        fun exec() {
            producer.fromIterable().subscribe(stringObserver)
        }

        fun execLambda() {
            val disposable = producer.fromIterable()
                    .subscribe({ s ->
                        println("onNext: $s")
                    }, { e ->
                        println("onError: ${e.message}")
                    }, {
                        println("onComplete")
                    })
        }
    }
}




