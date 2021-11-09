package com.example.androidlibrary.mvp.presenter


import android.util.Log
import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.githubrepositories.IGitHubRepositories
import com.example.androidlibrary.mvp.view.IRepositoryItemView
import com.example.androidlibrary.mvp.view.IRepositoryView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(val user: GithubUser?) : MvpPresenter<IRepositoryView>() {

    @Inject lateinit var repository: IGitHubRepositories
    @Inject lateinit var router: Router
    @Inject lateinit var screen: IScreens

    class RepositoriesListPresenter : IRepositoriesListPresenter {

        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null

        override fun getCount(): Int = repositories.size

        override fun bindView(view: IRepositoryItemView) {
            val repo = repositories[view.positionItem]
            view.setNameRepo(repo.name)
            view.setRepo(repo.html_url)
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()
    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { repoId ->
            openRepoInformation(repoId)
        }
    }

    private fun openRepoInformation(repoId: IRepositoryItemView) {
        router.navigateTo(screen.detailedUserRepo(repositoriesListPresenter.repositories[repoId.positionItem]))
    }

    private fun loadData() {
        disposable.addAll(repository.getRepositoriesList(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos ->
                    repositoriesListPresenter.repositories.clear()
                    repositoriesListPresenter.repositories.addAll(repos)
                    viewState.updateList()
                },
                { e ->
                    viewState.showError(e)
                    Log.e(RX_TAG, e.stackTraceToString())
                }
            )
        )
    }

    fun onBackCommandClick(): Boolean {
        router.exit()
        return true
    }

    companion object {
        const val RX_TAG = "RX_TAG"
    }
}