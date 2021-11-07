package com.example.androidlibrary.mvp.model.room

import androidx.room.*

@Dao
interface RepositoryDAO {

    @Query("SELECT * FROM RoomGitHubRepository")
    fun getAll(): List<RoomGitHubRepository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: RoomGitHubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReposList(repos: List<RoomGitHubRepository>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(repo: RoomGitHubRepository)

    @Delete
    fun delete(repo: RoomGitHubRepository)

    @Update
    fun updateUser(repo: RoomGitHubRepository)

    @Update
    fun updateListUsers(repos: List<RoomGitHubRepository>)

    @Query("SELECT * FROM RoomGitHubRepository WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomGitHubRepository?

    @Query("SELECT * FROM RoomGitHubRepository WHERE url = :url LIMIT 1")
    fun findByUrl(url: String?): RoomGitHubRepository?

    @Query("SELECT * FROM RoomGitHubRepository WHERE user_id = :userId")
    fun findForUser(userId: String?): List<RoomGitHubRepository>
}