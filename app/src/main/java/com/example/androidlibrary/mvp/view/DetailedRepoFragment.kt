package com.example.androidlibrary.mvp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlibrary.App
import com.example.androidlibrary.databinding.FragmentDetailedRepoBinding
import com.example.androidlibrary.mvp.model.data.GithubRepository
import com.example.androidlibrary.mvp.presenter.DetailedRepoPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailedRepoFragment : MvpAppCompatFragment(), IDetailedRepoView, BackButtonListener {

    companion object {
        const val REPO = "REPO"

        fun newInstance(repo: GithubRepository) = DetailedRepoFragment().apply {
            arguments = Bundle().apply { putParcelable(REPO, repo) }
        }
    }

    private var binding: FragmentDetailedRepoBinding? = null

    val presenter by moxyPresenter {
        DetailedRepoPresenter(arguments?.getParcelable(REPO)).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentDetailedRepoBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun setCountOfForks(count: Int?) {
        binding?.textViewCountOfForks?.text = "Count of forks: $count"
    }

    @SuppressLint("SetTextI18n")
    override fun setRepoName(name: String?) {
        binding?.textViewRepository?.text = "Repository name: $name"
    }

    @SuppressLint("SetTextI18n")
    override fun setRepoLanguage(language: String?) {
        binding?.textViewRepoLanguage?.text = "Language: $language"
    }

    override fun backPressed(): Boolean {
        return presenter.onBackCommandClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}