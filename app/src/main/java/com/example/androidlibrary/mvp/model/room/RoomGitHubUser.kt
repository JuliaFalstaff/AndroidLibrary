package com.example.androidlibrary.mvp.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser (
        @PrimaryKey var id: String,
        var login: String,
        @ColumnInfo(name = "avatar_url") var avatarUrl: String,
        @ColumnInfo(name = "repos_url") var reposUrl: String
)