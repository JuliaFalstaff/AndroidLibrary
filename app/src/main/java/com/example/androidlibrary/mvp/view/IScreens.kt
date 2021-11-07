package com.example.androidlibrary.mvp.view

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun detailedUser(user: GithubUser): Screen
    fun detailedUserRepo(repoId: GithubRepository): Screen
}

