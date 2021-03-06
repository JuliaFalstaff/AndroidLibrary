package com.example.androidlibrary.mvp.presenter

import android.util.Log
import com.example.androidlibrary.di.users.IUserScopeContainer
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.user.IGitHubUsersRepo
import com.example.androidlibrary.mvp.view.IScreens
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter() :
    MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IGitHubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screen: IScreens

    @Inject lateinit var usersScopeContainer: IUserScopeContainer

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.positionItem]
            view.setLogin(user.login)
            view.loadAvatar(user.avatar_url)
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

        disposable.addAll(
            usersRepo.getUsersList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { repos ->
                        usersListPresenter.users.clear()
                        usersListPresenter.users.addAll(repos)
                        viewState.updateList()
                    },
                    { e ->
                        viewState.showError(e)
                        Log.e(RX_TAG, e.stackTraceToString())
                    },
                )
        )

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        usersScopeContainer.releaseUsersScope()
        super.onDestroy()
        disposable.clear()
    }

    companion object {
        const val RX_TAG = "RX_TAG"
    }
}