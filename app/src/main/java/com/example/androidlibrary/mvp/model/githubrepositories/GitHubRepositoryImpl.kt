//package com.example.androidlibrary.mvp.model.githubrepositories
//
//import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
//import com.example.androidlibrary.mvp.model.data.GithubRepository
//import com.example.androidlibrary.mvp.model.data.GithubUser
//import com.example.androidlibrary.mvp.model.room.ILocalDataBase
//import com.example.androidlibrary.mvp.network.INetworkStatus
//import io.reactivex.rxjava3.core.Single
//
//class GitHubRepositoryImpl (private val api: RetrofitAPI, val networkStatus: INetworkStatus, val cache: ILocalDataBase) : IGitHubRepositories {
//
//
//    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> {}
//
//    override fun getRepository(url: String?): Single<GithubRepository> {
//        TODO("Not yet implemented")
//    }
//
////    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> = api.loadRepositories(url).subscribeOn(Schedulers.io())
////
////    override fun getRepository(url: String?): Single<GithubRepository> = api.loadRepository(url).subscribeOn(Schedulers.io())
//
//
//    override fun getUsers(): Single<List<GithubUser>> {
//        TODO("Not yet implemented")
//    }
//}