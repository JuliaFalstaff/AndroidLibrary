package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.RetrofitAPI
import com.example.androidlibrary.mvp.model.data.GithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubRepositoryImpl (private val api: RetrofitAPI) : IGitHubRepositories {

    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> = api.loadRepositories(url).subscribeOn(Schedulers.io())

    override fun getRepository(url: String?): Single<GithubRepository> = api.loadRepository(url).subscribeOn(Schedulers.io())
}