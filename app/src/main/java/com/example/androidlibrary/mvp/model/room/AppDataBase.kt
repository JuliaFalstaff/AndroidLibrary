package com.example.androidlibrary.mvp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidlibrary.R

@Database(
    exportSchema = false, entities = [
        RoomGitHubUser::class,
        RoomGitHubRepository::class
    ], version = 15
)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val repositoryDao: RepositoryDAO

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: AppDataBase? = null
        fun getInstance() = instance
            ?: throw RuntimeException(R.string.error_instance_db.toString())

//        fun getDatabase(context: Context?): AppDataBase {
//            if (instance == null) {
//                instance =
//                    Room.databaseBuilder(requireNotNull(context), AppDataBase::class.java, DB_NAME)
//                        .fallbackToDestructiveMigration()
//                        .build()
//            }
//            return requireNotNull(instance) { R.string.error_get_db.toString() }
//        }
    }
}