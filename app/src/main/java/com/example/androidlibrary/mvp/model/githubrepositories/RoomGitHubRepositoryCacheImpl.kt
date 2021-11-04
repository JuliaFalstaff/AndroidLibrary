package com.example.androidlibrary.mvp.model.githubrepositories

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.model.room.RoomGitHubRepository

class RoomGitHubRepositoryCacheImpl(private val db: AppDataBase) : IRoomGitHubRepositoryCache {


    override fun saveRepositories(repositories: List<GithubRepository>, userUrl: String?) {

        val roomRepositories = repositories.map { repo ->
            RoomGitHubRepository(
                    repo.id ?: 0,
                    repo.name ?: "",
                    repo.forks_count ?: 0,
                    repo.html_url ?: "",
                    repo.language ?: "",
                    userUrl,
                    repo.url ?: ""
            )
        }
        db.repositoryDao.insertReposList(roomRepositories)
    }

    override fun getRepositoriesList(url: String?): List<GithubRepository> {
        db.repositoryDao.findForUserRepo(url) ?: throw RuntimeException("No such repo in cache")
        return db.repositoryDao.findForUserRepo(url)
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
