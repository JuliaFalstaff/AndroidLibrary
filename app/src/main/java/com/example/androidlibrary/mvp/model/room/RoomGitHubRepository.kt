package com.example.androidlibrary.mvp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubRepository(
    @PrimaryKey
    var id: Int,
    var name: String?,
    var forks_count: Int?,
    var html_url: String?,
    var language: String?,
    var userUrl: String?,
    var url: String?
)