package com.example.androidlibrary.mvp.model.room

import androidx.room.*

@Dao
interface UserDAO {

    @Query("SELECT * FROM RoomGitHubUser")
    fun getAll(): List<RoomGitHubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: RoomGitHubUser)

    @Delete
    fun delete(user: RoomGitHubUser)

    @Update
    fun updateUser (user: RoomGitHubUser)

    @Update
    fun updateListUsers (users: List<RoomGitHubUser>)

    @Query("SELECT * FROM RoomGitHubUser WHERE id = :id LIMIT 1")
    fun findById(id: Int) : RoomGitHubRepository?
}