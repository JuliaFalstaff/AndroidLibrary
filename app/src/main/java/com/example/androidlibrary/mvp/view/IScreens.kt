package com.example.androidlibrary.mvp.view

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun detailedUser(repoUrl: String?): Screen
    fun detailedUserRepo(repoId: GithubRepository) : Screen
}

