package com.example.androidlibrary.mvp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: String,
    val login: String,
    val avatar_url: String,
    val repos_url: String
) : Parcelable
