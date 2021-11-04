package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositories {
    fun getRepositoriesList(url: String?): Single<List<GithubRepository>>
}