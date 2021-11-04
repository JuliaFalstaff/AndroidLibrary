package com.example.androidlibrary.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.App
import com.example.androidlibrary.databinding.FragmentRepositoryBinding
import com.example.androidlibrary.mvp.adapter.RepositoriesAdapter
import com.example.androidlibrary.mvp.model.RetrofitImpl
import com.example.androidlibrary.mvp.model.githubrepositories.GitHubRepositoryImpl
import com.example.androidlibrary.mvp.presenter.RepositoryPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), IRepositoryView,
        BackButtonListener {
    companion object {
        const val REPO = "Repo"

        fun newInstance(repo: String?): RepositoryFragment {
            val args = Bundle().apply { putString(REPO, repo) }
            val fragment = RepositoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var binding: FragmentRepositoryBinding? = null
    private var adapter: RepositoriesAdapter? = null

    val presenter by moxyPresenter {
        RepositoryPresenter(
                (arguments?.getString(REPO)),
                GitHubRepositoryImpl(RetrofitImpl().api),
                App.instance.router,
                AndroidScreens()
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View =
            FragmentRepositoryBinding.inflate(inflater, container, false).also {
                binding = it
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun init() {
        binding?.run {
            this.recyclerViewDetailedUserInfo.layoutManager = LinearLayoutManager(context)
            adapter = RepositoriesAdapter(presenter.repositoriesListPresenter)
            this.recyclerViewDetailedUserInfo.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun backPressed(): Boolean {
        return presenter.onBackCommandClick()
    }

}