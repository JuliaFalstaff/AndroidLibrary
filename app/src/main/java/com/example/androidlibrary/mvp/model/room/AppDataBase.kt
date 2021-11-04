package com.example.androidlibrary.mvp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomGitHubUser::class, RoomGitHubUser::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun repositoryDao(): RepositoryDAO
}