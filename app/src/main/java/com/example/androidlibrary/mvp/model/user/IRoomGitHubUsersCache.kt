package com.example.androidlibrary.mvp.model.user

import com.example.androidlibrary.mvp.model.data.GithubUser

interface IRoomGitHubUsersCache {
    fun saveToDB(users: List<GithubUser>)
    fun getUserList(): List<GithubUser>
}