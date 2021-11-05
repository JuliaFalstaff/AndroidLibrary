package com.example.androidlibrary.mvp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    exportSchema = false, entities = [
        RoomGitHubUser::class,
        RoomGitHubRepository::class
    ], version = 7
)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repositoryDao: RepositoryDAO

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: AppDataBase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun getDatabase(context: Context?): AppDataBase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(requireNotNull(context), AppDataBase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return requireNotNull(instance) { "Database has not been created." }
        }
    }
}