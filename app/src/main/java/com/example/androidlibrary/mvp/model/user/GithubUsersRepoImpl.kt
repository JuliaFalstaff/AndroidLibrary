//package com.example.androidlibrary.mvp.model.user
//
//
//import com.example.androidlibrary.mvp.model.data.GithubRepository
//import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
//import com.example.androidlibrary.mvp.model.data.GithubUser
//import com.example.androidlibrary.mvp.model.room.AppDataBase
//import com.example.androidlibrary.mvp.model.room.RoomGitHubUser
//import com.example.androidlibrary.mvp.network.INetworkStatus
//import io.reactivex.rxjava3.core.Single
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//class GithubUsersRepoImpl(private val api: RetrofitAPI, val networkStatus: INetworkStatus, val db: AppDataBase) : IGitHubUsersRepo {
//
//
//    override fun getUsersList(): Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            api.loadUsers().flatMap { users ->
//                Single.fromCallable {
//                    val roomUsers = users.map { user ->
//                        RoomGitHubUser(user.id ?: "", user.login ?: "", user.avatar_url
//                                ?: "", user.repos_url ?: "")
//                    }
//                    db.userDao.insertUserList(roomUsers)
//                    users
//                }
//            }
//        } else {
//            Single.fromCallable{
//                db.userDao.getAll().map { roomGitHubUser ->
//                    GithubUser(roomGitHubUser.id, roomGitHubUser.login, roomGitHubUser.avatar_url, roomGitHubUser.repos_url)
//                }
//            }
//        }.subscribeOn(Schedulers.io())
//
//    }
//
////    override fun getUsersList(): Single<List<GithubUser>> = api.loadUsers().subscribeOn(Schedulers.io())
//
//}