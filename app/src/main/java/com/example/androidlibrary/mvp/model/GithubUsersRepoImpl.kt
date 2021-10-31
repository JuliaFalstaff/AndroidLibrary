package com.example.androidlibrary.mvp.model

import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoImpl(private val client: RetrofitAPI) : IGitHubUsersRepo {

    override fun getUsersList(): Single<List<GithubUser>> = client.loadUsers().subscribeOn(Schedulers.io())
}