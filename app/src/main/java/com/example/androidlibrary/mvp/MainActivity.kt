package com.example.androidlibrary.mvp

import android.os.Bundle
import com.example.androidlibrary.App
import com.example.androidlibrary.R
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.androidlibrary.mvp.presenter.MainPresenter
import com.example.androidlibrary.mvp.view.BackButtonListener
import com.example.androidlibrary.mvp.view.IMainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    @Inject
    lateinit var navigationHolder: NavigatorHolder
    val navigator = AppNavigator(this, R.id.container)

    private var binding: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}