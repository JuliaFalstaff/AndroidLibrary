package com.example.androidlibrary.mvp.model.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["repos_url"], unique = true)])
data class RoomGitHubUser (
        @PrimaryKey var id: Int,
        var login: String,
        var avatar_url: String,
         var repos_url: String
)