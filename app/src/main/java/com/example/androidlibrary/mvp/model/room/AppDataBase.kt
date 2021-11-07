package com.example.androidlibrary.mvp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    exportSchema = false, entities = [
        RoomGitHubUser::class,
        RoomGitHubRepository::class
    ], version = 17
)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repositoryDao: RepositoryDAO
}