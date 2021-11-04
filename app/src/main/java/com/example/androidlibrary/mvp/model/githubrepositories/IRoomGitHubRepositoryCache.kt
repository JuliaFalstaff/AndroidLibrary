package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository

interface IRoomGitHubRepositoryCache {

    fun saveRepositories(repositories: List<GithubRepository>, userUrl: String?)
    fun getRepositoriesList(url: String?): List<GithubRepository>
}