package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositories {
    fun getRepositoriesList(user: GithubUser?): Single<List<GithubRepository>>
}