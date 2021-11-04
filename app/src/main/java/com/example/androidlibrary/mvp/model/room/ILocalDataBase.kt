package com.example.androidlibrary.mvp.model.room

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.githubrepositories.IGitHubRepositories

interface ILocalDataBase : IGitHubRepositories {
    fun insertUsers(users: List<GithubUser>)
    fun insertRepositories (repositories: List<GithubRepository>, userUrl: String?)
}