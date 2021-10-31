package com.example.androidlibrary.mvp.view

import com.example.androidlibrary.mvp.model.data.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun detailedUser(user: GithubUser): Screen {
        return FragmentScreen { DetailedUserFragment.newInstance(user) }
    }
}