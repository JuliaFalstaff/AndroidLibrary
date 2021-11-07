package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser

interface IRoomGitHubRepositoryCache {

    fun saveRepositories(repositories: List<GithubRepository>, user: GithubUser?)
    fun getRepositoriesList(user: GithubUser?): List<GithubRepository>
}