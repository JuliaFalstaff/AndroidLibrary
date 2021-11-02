package com.example.androidlibrary.mvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlibrary.databinding.DetailedUserItemRecyclerBinding
import com.example.androidlibrary.databinding.FragmentDetailedUserBinding
import com.example.androidlibrary.mvp.presenter.IRepositoriesListPresenter
import com.example.androidlibrary.mvp.view.IRepositoryItemView
import com.example.androidlibrary.mvp.view.avatar.IImageLoader

class RepositoriesAdapter(val presenter: IRepositoriesListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DetailedUserItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder (private val binding: DetailedUserItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root), IRepositoryItemView {

        override fun setRepo(url: String?) {
            binding.textViewUrl.text = url
        }

        override fun setNameRepo(name: String?) {
            binding.textViewName.text = name
        }

        override var positionItem: Int = -1
    }
}