package com.example.androidlibrary.mvp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
        val id: Int,
        val name: String,
        val forks_count: Int?,
        val html_url: String?,
        val isPrivate: Boolean,
        val language: String
) : Parcelable
