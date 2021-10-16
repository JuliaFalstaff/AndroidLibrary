package com.example.androidlibrary.mvp.model


import android.provider.Settings.Global.getString
import com.example.androidlibrary.R


class GithubUsersRepo {
    private val repositories = (1..10).map {
        GithubUser("Login $it")
    }

    fun getUsers() : List<GithubUser> {
        return repositories
    }
}