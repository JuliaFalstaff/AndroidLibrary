package com.example.androidlibrary.mvp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
        val id: String,
        val name: String?,
        val forks_count: Int?,
        val html_url: String?,
        val language: String?,
        val url: String?
) : Parcelable
