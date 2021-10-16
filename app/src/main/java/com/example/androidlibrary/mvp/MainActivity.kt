package com.example.androidlibrary.mvp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.App
import com.example.androidlibrary.R
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.presenter.MainPresenter
import com.example.androidlibrary.mvp.presenter.UsersPresenter
import com.example.androidlibrary.mvp.view.AndroidScreens
import com.example.androidlibrary.mvp.view.BackButtonListener
import com.example.androidlibrary.mvp.view.IMainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    val navigator = AppNavigator(this, R.id.container)

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
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
