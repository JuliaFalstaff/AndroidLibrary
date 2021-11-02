package com.example.androidlibrary.mvp.presenter


import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.view.IDetailedUserView
import com.example.androidlibrary.mvp.view.IRepositoryItemView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailedUsersPresenter(
    val user: GithubUser?,
    val router: Router,
    val screen: IScreens
) :
    MvpPresenter<IDetailedUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.login?.let { viewState.setUserLogin(it) }
        user?.avatar_url.let { viewState.setLoadAvatar(it) }
    }

    fun onBackCommandClick(): Boolean {
        router.backTo(screen.users())
        return true
    }



    class RepositoriesListPresenter : IRepositoriesListPresenter {

        val gitHubRepositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null

        override fun getCount(): Int = gitHubRepositories.size

        override fun bindView(view: IRepositoryItemView) {
            val repo = gitHubRepositories[view.positionItem]
            view.setNameRepo(repo.name)
            view.setRepo(repo.html_url)
        }
    }
}