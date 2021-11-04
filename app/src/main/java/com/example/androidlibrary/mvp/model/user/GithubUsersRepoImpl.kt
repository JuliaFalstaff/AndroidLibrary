package com.example.androidlibrary.mvp.model.user


import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepoImpl(
    private val api: RetrofitAPI,
    val networkStatus: INetworkStatus,
    val db: IRoomGitHubUsersCache
) : IGitHubUsersRepo {

    override fun getUsersList(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    api.loadUsers()
                        .flatMap { users ->
                            Single.fromCallable {
                                db.saveToDB(users)
                                users
                            }
                        }
                        .onErrorReturn {
                            db.getUserList()
                        }
                } else {
                    Single.fromCallable {
                        db.getUserList()
                    }
                }.subscribeOn(Schedulers.io())
            }
//    override fun getUsersList(): Single<List<GithubUser>> = api.loadUsers().subscribeOn(Schedulers.io())

}