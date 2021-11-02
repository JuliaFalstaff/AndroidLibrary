package com.example.androidlibrary.mvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlibrary.databinding.UserItemBinding
import com.example.androidlibrary.mvp.presenter.IUserListPresenter
import com.example.androidlibrary.mvp.view.IUserItemView
import com.example.androidlibrary.mvp.view.avatar.IImageLoader

class UsersAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root), IUserItemView {

        override var positionItem: Int = -1

        override fun setLogin(text: String) = with(binding) {
            textViewLogin.text = text
        }

        override fun loadAvatar(url: String) = with(binding) {
            imageLoader.loadInto(url, imageViewAvatar)
        }
    }
}
