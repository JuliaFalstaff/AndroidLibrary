package com.example.androidlibrary.mvp.model.room

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersAndRepositories {
    fun getRepositoriesList(url: String?) : Single<List<GithubRepository>>
    fun getRepository(url: String?) : Single<GithubRepository>
    fun getUsers() : Single<List<GithubUser>>
}