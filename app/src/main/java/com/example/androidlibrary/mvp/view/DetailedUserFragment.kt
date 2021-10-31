package com.example.androidlibrary.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlibrary.App
import com.example.androidlibrary.databinding.FragmentDetailedUserBinding
import com.example.androidlibrary.mvp.model.data.GithubUser
import com.example.androidlibrary.mvp.presenter.DetailedUsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailedUserFragment : MvpAppCompatFragment(), IDetailedUserView,
    BackButtonListener {
    companion object {
        const val USER = "User"

        fun newInstance(user: GithubUser): DetailedUserFragment {
            val args = Bundle().apply { putParcelable(USER, user) }
            val fragment = DetailedUserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var binding: FragmentDetailedUserBinding? = null

    val presenter by moxyPresenter {
        DetailedUsersPresenter(
            (arguments?.getParcelable(USER)),
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setUserLogin(login: String?) {
        binding?.textViewLoginUser?.text = login
    }

    override fun backPressed(): Boolean {
        return presenter.onBackCommandClick()
    }
}