package com.example.androidlibrary.mvp.view

import com.example.androidlibrary.mvp.model.data.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun detailedUser(user: GithubUser): Screen
}

