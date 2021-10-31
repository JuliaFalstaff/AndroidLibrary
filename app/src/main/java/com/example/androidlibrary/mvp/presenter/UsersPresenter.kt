package com.example.androidlibrary.mvp.presenter

import android.util.Log
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.IGitHubUsersRepo
import com.example.androidlibrary.mvp.view.IScreens
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: IGitHubUsersRepo, val router: Router, val screen: IScreens) :
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
    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            openDetailedUserInfo(userItemView)
        }
    }

    private fun openDetailedUserInfo(userItemView: IUserItemView) {
        router.navigateTo(screen.detailedUser(usersListPresenter.users[userItemView.positionItem]))
    }

    private fun loadData() {

        disposable.addAll(usersRepo.getUsersList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(repos)
                    viewState.updateList()
                },
                { e -> Log.i(RX_TAG, e?.localizedMessage.toString()) },
        ))

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    companion object {
        const val RX_TAG = "RX_TAG"
    }
}