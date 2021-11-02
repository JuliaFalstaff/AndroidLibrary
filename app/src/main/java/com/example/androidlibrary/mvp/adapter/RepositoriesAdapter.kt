package com.example.androidlibrary.mvp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlibrary.databinding.RepositoryItemRecyclerBinding
import com.example.androidlibrary.mvp.presenter.IRepositoriesListPresenter
import com.example.androidlibrary.mvp.view.IRepositoryItemView

class RepositoriesAdapter(val presenter: IRepositoriesListPresenter) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RepositoryItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return presenter.bindView(holder.apply {
            positionItem = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(private val binding: RepositoryItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root), IRepositoryItemView {

        @SuppressLint("SetTextI18n")
        override fun setRepo(url: String?) {
            binding.textViewRepoUrl.text = "Repo url: $url"
        }

        @SuppressLint("SetTextI18n")
        override fun setNameRepo(name: String?) {
            binding.textViewRepoName.text = "Name: $name"
        }

        override var positionItem: Int = -1
    }
}