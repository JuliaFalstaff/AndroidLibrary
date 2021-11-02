package com.example.androidlibrary.mvp.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun detailedUser(repoUrl: String?): Screen {
        return FragmentScreen { RepositoryFragment.newInstance(repoUrl) }
    }
}