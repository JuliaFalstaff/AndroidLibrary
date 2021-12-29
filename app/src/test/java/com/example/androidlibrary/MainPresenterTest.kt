package com.example.androidlibrary

import com.example.androidlibrary.mvp.presenter.MainPresenter
import com.example.androidlibrary.mvp.view.IMainView
import com.example.androidlibrary.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    @Mock
    private lateinit var router: Router
    @Mock
    private lateinit var screen: IScreens
    @Mock
    private lateinit var view: IMainView

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = MainPresenter(router, screen)
    }

    @Test
    fun mainPresenter_FirstViewAttach() {
        presenter.attachView(view)
        verify(router, times(0)).navigateTo(screen.users())
    }

    @Test
    fun presenter_BackClicked() {
        presenter.backClicked()
        verify(router, atLeastOnce()).navigateTo(screen.users())
    }
}
