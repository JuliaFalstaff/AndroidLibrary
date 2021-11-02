package com.example.androidlibrary.mvp.model.user

import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsersList(): Single<List<GithubUser>>
}