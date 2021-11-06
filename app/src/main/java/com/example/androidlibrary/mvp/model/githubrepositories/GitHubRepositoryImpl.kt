package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.R
import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubRepositoryImpl(
    private val api: RetrofitAPI,
    val networkStatus: INetworkStatus,
    val db: IRoomGitHubRepositoryCache
) : IGitHubRepositories {
    override fun getRepositoriesList(user: GithubUser?) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user?.repos_url?.let { url ->
                    api.loadRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                db.saveRepositories(repositories, user)
                                repositories
                            }
                        }
                        .onErrorReturn {
                            db.getRepositoriesList(user)
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException(R.string.error_repo.toString()))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    db.getRepositoriesList(user)
                }
            }
        }.subscribeOn(Schedulers.io())
}