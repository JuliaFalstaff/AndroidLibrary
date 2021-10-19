package com.example.androidlibrary.mvp.model

class GithubUsersRepo {
    private val repositories = (1..10).map {
        GithubUser("Login $it")
    }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}