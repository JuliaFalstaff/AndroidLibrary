package com.example.androidlibrary

import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.model.user.IGitHubUsersRepo
import com.example.androidlibrary.mvp.presenter.UsersPresenter
import com.example.androidlibrary.mvp.view.IScreens
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersPresenterTest {

    lateinit var presenter: UsersPresenter

    @Mock
    lateinit var usersRepo: IGitHubUsersRepo

    @Mock
    lateinit var router: Router

    @Mock
    lateinit var screen: IScreens

    @Mock
    lateinit var view: UsersView

    @Mock
    lateinit var viewItem: IUserItemView

    @Mock
    lateinit var listPresenter: UsersPresenter.UsersListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UsersPresenter(usersRepo, router, screen)
    }

    @Test
    fun loadDataSuccess() {
        Mockito.doReturn(Single.just(GithubUser::class.java)).`when`(usersRepo).getUsersList()
        val testObserver: @NonNull TestObserver<List<GithubUser>>? =
                usersRepo.getUsersList()
                        .observeOn(TestScheduler())
                        .subscribeOn(TestScheduler())
                        .test()
        usersRepo.getUsersList()
        verify(usersRepo, atLeastOnce()).getUsersList()
        testObserver?.dispose()
    }

    @Test
    fun onBackPressed() {
        presenter.backPressed()
        verify(router, atLeastOnce()).exit()
    }

    @Test
    fun openDetailedUserInfo() {
        val item = viewItem
        router.navigateTo(screen.detailedUser(any()))
        verify(router, atLeastOnce()).navigateTo(screen.detailedUser(any()))
    }
}