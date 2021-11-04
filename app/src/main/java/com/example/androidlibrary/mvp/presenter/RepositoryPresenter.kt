package com.example.androidlibrary.mvp.presenter


import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.githubrepositories.IGitHubRepositories
import com.example.androidlibrary.mvp.model.room.IGitHubUsersAndRepositories
import com.example.androidlibrary.mvp.view.IRepositoryItemView
import com.example.androidlibrary.mvp.view.IRepositoryView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class RepositoryPresenter(
    val repoUrl: String?,
    val repository: IGitHubUsersAndRepositories,
    val router: Router,
    val screen: IScreens,
) :
        MvpPresenter<IRepositoryView>() {

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
        disposable.addAll(repository.getRepositoriesList(repoUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { repos ->
                            repositoriesListPresenter.repositories.clear()
                            repositoriesListPresenter.repositories.addAll(repos)
                            viewState.updateList()
                        },
                        { e -> viewState.showError(e) }
                )
        )
    }

    fun onBackCommandClick(): Boolean {
        router.backTo(screen.users())
        return true
    }
}