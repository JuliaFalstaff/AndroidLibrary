package com.example.androidlibrary.mvp.model

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {
    @GET("/users")
    fun loadUsers() : Single<List<GithubUser>>

    @GET
    fun loadRepositories(@Url url: String?) : Single<List<GithubRepository>>

    @GET
    fun loadRepository(@Url url: String?) : Single<GithubRepository>
}