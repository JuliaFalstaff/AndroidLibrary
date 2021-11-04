package com.example.androidlibrary.mvp.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubRepository (
    @PrimaryKey
    var id: Int = 0,
    var name: String? = "",
    var forks_count: Int? = 0,
    var html_url: String?  = "",
    var private: Boolean = false,
    var language: String?  = "",
    var userUrl: String?  = "",
    var url: String?  = ""
)