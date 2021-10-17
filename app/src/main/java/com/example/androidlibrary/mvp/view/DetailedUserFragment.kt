package com.example.androidlibrary.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlibrary.App
import com.example.androidlibrary.databinding.FragmentDetailedUserBinding
import com.example.androidlibrary.mvp.model.GithubUsersRepo
import com.example.androidlibrary.mvp.presenter.DetailedUsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailedUserFragment(val positionUser: Int) : MvpAppCompatFragment(), IDetailedUserView,
    BackButtonListener {
    companion object {
        fun newInstance(positionUser: Int) = DetailedUserFragment(positionUser)
    }

    private var binding: FragmentDetailedUserBinding? = null
    val presenter by moxyPresenter {
        DetailedUsersPresenter(
            GithubUsersRepo(),
            App.instance.router,
            AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDetailedUserBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun init() {
        setUserLogin()
    }

    override fun setUserLogin() {
        binding?.textViewLoginUser?.text = presenter.setUserLogin(positionUser)
    }

    override fun backPressed(): Boolean {
        return presenter.onBackCommandClick()
    }
}