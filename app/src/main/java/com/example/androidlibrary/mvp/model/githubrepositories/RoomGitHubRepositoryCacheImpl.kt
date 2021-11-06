package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.R
import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.model.room.RoomGitHubRepository

class RoomGitHubRepositoryCacheImpl(private val db: AppDataBase) : IRoomGitHubRepositoryCache {

    override fun saveRepositories(repositories: List<GithubRepository>, user: GithubUser?) {
        val roomUser = user?.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException(R.string.error_find_user.toString())

        val roomRepositories = repositories.map {
            RoomGitHubRepository(
                it.id,
                it.name,
                it.forks_count,
                it.html_url,
                it.language,
                it.url,
                roomUser.id
            )
        }
        db.repositoryDao.insertReposList(roomRepositories)
    }

    override fun getRepositoriesList(user: GithubUser?): List<GithubRepository> {
        val roomUser = user?.login?.let {
            db.userDao.findByLogin(it)
        } ?: throw RuntimeException(R.string.error_find_user.toString())

        return db.repositoryDao.findForUser(roomUser.id)
            .map {
                GithubRepository(
                    it.id,
                    it.name,
                    it.forks_count,
                    it.html_url,
                    it.language,
                    it.url
                )
            }
    }
}
