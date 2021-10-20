package com.example.androidlibrary.mvp.presenter

import android.util.Log
import com.example.androidlibrary.mvp.model.GithubUser
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.view.IScreens
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screen: IScreens) :
        MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.positionItem]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    val users = usersRepo.getUsers()
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        exec()
        viewState.updateList()
        usersListPresenter.itemClickListener = { userItemView ->
            openDetailedUserInfo(userItemView)
        }
    }

    private fun openDetailedUserInfo(userItemView: IUserItemView) {
        router.navigateTo(screen.detailedUser(userItemView.positionItem))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private fun exec() {
        Consumer(Producer()).execFromIterable()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    //Observable
    inner class Producer() {
        fun fromIterable(): Observable<GithubUser> {
            return Observable.fromIterable(users)
        }
    }

    inner class Consumer(val producer: Producer) {

        val userObserver = object : Observer<GithubUser> {
            override fun onSubscribe(d: Disposable?) {
                disposable = d
                Log.i(RX_TAG, d.toString())
            }

            override fun onNext(t: GithubUser?) {
                Log.i(RX_TAG, t.toString())
                t?.let {
                    usersListPresenter.users.add(it)
                }
            }

            override fun onError(e: Throwable?) {
                Log.i(RX_TAG, e?.localizedMessage.toString())
            }

            override fun onComplete() {
                Log.i(RX_TAG, "onCompete")
            }
        }

        fun execFromIterable() {
            producer.fromIterable()
                    .subscribe(userObserver)
        }
    }

    companion object {
        const val RX_TAG = "RX_TAG"
    }
}