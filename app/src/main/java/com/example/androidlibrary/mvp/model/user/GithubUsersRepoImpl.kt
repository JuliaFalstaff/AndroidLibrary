package com.example.androidlibrary.mvp.model.user

import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoImpl(private val api: RetrofitAPI) : IGitHubUsersRepo {

    override fun getUsersList(): Single<List<GithubUser>> = api.loadUsers().subscribeOn(Schedulers.io())
}