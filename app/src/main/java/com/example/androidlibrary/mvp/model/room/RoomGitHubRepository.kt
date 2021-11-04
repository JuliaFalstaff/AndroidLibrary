package com.example.androidlibrary.mvp.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubRepository (
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "forks_count") val forksCount: Int?,
    @ColumnInfo(name = "html_url")val htmlUrl: String?,
    val private: Boolean,
    val language: String
)