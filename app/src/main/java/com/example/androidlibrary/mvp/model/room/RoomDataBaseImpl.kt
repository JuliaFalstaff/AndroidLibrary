package com.example.androidlibrary.mvp.model.room

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomDataBaseImpl (private val db: AppDataBase = AppDataBase.getInstance()) : ILocalDataBase {

    override fun insertUsers(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGitHubUser(user.id, user.login, user.avatar_url, user.repos_url)
        }
        db.userDao.insertUserList(roomUsers)
    }

    override fun insertRepositories(repositories: List<GithubRepository>, userUrl: String?) {
        val roomRepositories = repositories.map { repo ->
            RoomGitHubRepository(repo.id, repo.name, repo.forks_count, repo.html_url, repo.isPrivate, repo.language, userUrl, repo.url)
        }
        db.repositoryDao.insertReposList(roomRepositories)
    }

    override fun getRepositoriesList(url: String?): Single<List<GithubRepository>> = Single.fromCallable {
        db.repositoryDao.findForUserRepo(url).map { repo ->
            GithubRepository(repo.id, repo.name, repo.forks_count, repo.html_url, repo.private, repo.language, repo.url)
        }
    }

    override fun getRepository(url: String?): Single<GithubRepository> = Single.fromCallable {
        db.repositoryDao.findByUrl(url).let {
            it?.let { repo -> GithubRepository(repo.id, repo.name, repo.forks_count, repo.html_url, repo.private, repo.language, repo.url) }
        }
    }

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map {
            GithubUser(it.id, it.login, it.avatar_url, it.repos_url)
        }
    }.subscribeOn(Schedulers.io())
}