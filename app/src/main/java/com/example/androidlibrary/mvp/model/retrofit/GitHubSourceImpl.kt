package com.example.androidlibrary.mvp.model.retrofit

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.room.IGitHubUsersAndRepositories
import com.example.androidlibrary.mvp.model.room.ILocalDataBase
import com.example.androidlibrary.mvp.model.room.RoomDataBaseImpl
import com.example.androidlibrary.mvp.network.AndroidNetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubSourceImpl(
    private val retrofit: RetrofitUsersReposImpl,
    val db: RoomDataBaseImpl,
    val network: AndroidNetworkStatus
) :
    IGitHubUsersAndRepositories {

    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> = network
        .isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                retrofit.getRepositoriesList(url).map { repos ->
                    db.insertRepositories(repos, url)
                    repos
                }
            } else {
                db.getRepositoriesList(url)
            }
        }.subscribeOn(Schedulers.io())

    override fun getRepository(url: String?): Single<GithubRepository> = network
        .isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                retrofit.getRepository(url)
            } else {
                db.getRepository(url)
            }
        }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GithubUser>> = network
        .isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                retrofit.getUsers().map { users ->
                    db.insertUsers(users)
                    users
                }
            } else {
                db.getUsers()
            }
        }.subscribeOn(Schedulers.io())
}