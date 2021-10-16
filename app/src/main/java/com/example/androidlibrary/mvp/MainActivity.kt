package com.example.androidlibrary.mvp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.presenter.MainPresenter
import com.example.androidlibrary.mvp.view.IMainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter(presenter.usersListPresenter)
        binding.recyclerViewUsers.adapter = adapter
    }

    override fun updateList() {
       adapter?.notifyDataSetChanged()
    }
}
