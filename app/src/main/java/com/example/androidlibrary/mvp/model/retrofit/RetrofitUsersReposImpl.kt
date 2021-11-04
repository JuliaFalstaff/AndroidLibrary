package com.example.androidlibrary.mvp.model.retrofit

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.room.IGitHubUsersAndRepositories
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUsersReposImpl (private val api: RetrofitAPI) : RemoteSource {
    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> = api.loadRepositories(url).subscribeOn(Schedulers.io())

    override fun getRepository(url: String?): Single<GithubRepository> = api.loadRepository(url).subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> = api.loadUsers().subscribeOn(Schedulers.io())
}