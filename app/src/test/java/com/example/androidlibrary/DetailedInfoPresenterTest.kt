package com.example.androidlibrary

import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.presenter.DetailedRepoPresenter
import com.example.androidlibrary.mvp.view.IDetailedRepoView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailedInfoPresenterTest {
    private lateinit var repoPresenter: DetailedRepoPresenter

    @Mock
    private lateinit var repository: GithubRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screen: IScreens

    @Mock
    private lateinit var view: IDetailedRepoView

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repoPresenter = DetailedRepoPresenter(repository, router, screen)
    }

    @Test
    fun detailedRepo_FirstViewAttach() {
        val name = "name"
        val forks = 2
        val repoLanguage = "kotlin"

        repoPresenter.attachView(view)
        view.setRepoName(name)
        view.setCountOfForks(forks)
        view.setRepoLanguage(repoLanguage)

        verify(view, atLeastOnce()).setRepoName(name)
        verify(view, atLeastOnce()).setRepoLanguage(repoLanguage)
        verify(view, atLeastOnce()).setCountOfForks(forks)
    }

    @Test
    fun detailedRepo_onBackCommandClick() {
        repoPresenter.onBackCommandClick()
        verify(router, atLeastOnce()).backTo(screen.users())
    }
}