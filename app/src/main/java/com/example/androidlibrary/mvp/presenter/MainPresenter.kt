package com.example.androidlibrary.mvp.presenter

import com.example.androidlibrary.mvp.model.GithubUser
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.view.IUserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<IMainView>() {

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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            //TODO переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

}