package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubRepositoryImpl(
    private val api: RetrofitAPI,
    val networkStatus: INetworkStatus,
    val db: IRoomGitHubRepositoryCache
) : IGitHubRepositories {
    override fun getRepositoriesList(url: String?) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                url?.let { url ->
                    api.loadRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                db.saveRepositories(repositories, url)
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    db.getRepositoriesList(url)
                }
            }
        }.subscribeOn(Schedulers.io())
}