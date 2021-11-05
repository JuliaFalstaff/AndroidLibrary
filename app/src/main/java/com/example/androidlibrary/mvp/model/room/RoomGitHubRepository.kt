package com.example.androidlibrary.mvp.model.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["repos_url"],
        childColumns = ["user_url"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitHubRepository(
    @PrimaryKey(autoGenerate = false) var id: Int,
    var name: String?,
    var forks_count: Int?,
    var html_url: String?,
    var language: String?,
    var user_url: String?,
    var url: String?
)