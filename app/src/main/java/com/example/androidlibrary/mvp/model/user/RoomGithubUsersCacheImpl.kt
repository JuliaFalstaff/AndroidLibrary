package com.example.androidlibrary.mvp.model.user

import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.room.AppDataBase
import com.example.androidlibrary.mvp.model.room.RoomGitHubUser

class RoomGithubUsersCacheImpl(private val db: AppDataBase) : IRoomGitHubUsersCache {
    override fun saveToDB(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGitHubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatar_url ?: "",
                user.repos_url ?: ""
            )
        }
        db.userDao.insertUserList(roomUsers)
    }

    override fun getUserList(): List<GithubUser> = db.userDao.getAll().map { user ->
        GithubUser(user.id, user.login, user.avatar_url, user.repos_url)
    }
}