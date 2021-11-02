package com.example.androidlibrary.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.App
import com.example.androidlibrary.databinding.FragmentUsersBinding
import com.example.androidlibrary.mvp.adapter.UsersAdapter
import com.example.androidlibrary.mvp.model.user.GithubUsersRepoImpl
import com.example.androidlibrary.mvp.model.RetrofitImpl
import com.example.androidlibrary.mvp.presenter.UsersPresenter
import com.example.androidlibrary.mvp.view.avatar.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var binding: FragmentUsersBinding? = null
    val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepoImpl(RetrofitImpl().api),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var adapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun init() {
        binding?.run {
            this.recyclerViewUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersAdapter(presenter.usersListPresenter, GlideImageLoader())
            this.recyclerViewUsers.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()
}

