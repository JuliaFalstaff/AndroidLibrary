package com.example.androidlibrary.mvp.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser (
        @PrimaryKey var id: String,
        var login: String,
        var avatar_url: String,
        var repos_url: String
)