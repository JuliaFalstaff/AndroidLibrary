package com.example.androidlibrary.mvp.presenter

import android.util.Log
import com.example.androidlibrary.R
import com.example.androidlibrary.mvp.model.GithubUser
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.view.IScreens
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
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
    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        viewState.updateList()
        usersListPresenter.itemClickListener = { userItemView ->
            openDetailedUserInfo(userItemView)
        }
    }

    private fun openDetailedUserInfo(userItemView: IUserItemView) {
        router.navigateTo(screen.detailedUser(userItemView.positionItem))
    }

    private fun loadData() {
        val producer = Producer()
        disposable = producer.fromIterable().subscribe(
                { user ->
                    Log.i(RX_TAG, user.toString())
                    usersListPresenter.users.add(user)
                },
                { e -> Log.i(RX_TAG, e?.localizedMessage.toString()) },
                { Log.i(RX_TAG, R.string.onComplete.toString()) }
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    //Observable
    inner class Producer() {
        fun fromIterable(): Observable<GithubUser> {
            return Observable.fromIterable(usersRepo.getUsers())
        }
    }

    companion object {
        const val RX_TAG = "RX_TAG"
    }
}