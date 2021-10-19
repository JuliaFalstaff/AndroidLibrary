package com.example.androidlibrary.mvp.model

import android.os.Parcelable
import com.example.androidlibrary.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val loginImage: String = R.drawable.avatars_demo.toString()
) : Parcelable
