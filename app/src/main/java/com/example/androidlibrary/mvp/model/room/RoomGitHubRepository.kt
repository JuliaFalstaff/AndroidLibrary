package com.example.androidlibrary.mvp.model.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitHubRepository(
    @PrimaryKey
    var id: String,
    var name: String?,
    var forks_count: Int?,
    var html_url: String?,
    var language: String?,
    var url: String?,
    var user_id: String
)