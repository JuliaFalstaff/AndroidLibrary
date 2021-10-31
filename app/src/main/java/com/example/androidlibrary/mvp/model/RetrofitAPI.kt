package com.example.androidlibrary.mvp.model

import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("/users")
    fun loadUsers() : Single<List<GithubUser>>
}