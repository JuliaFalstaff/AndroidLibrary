package com.example.androidlibrary.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlibrary.databinding.FragmentDetailedUserBinding
import moxy.MvpAppCompatFragment

class DetailedUserFragment : MvpAppCompatFragment(){
    companion object {
        fun newInstance() = DetailedUserFragment()
    }

    private var binding: FragmentDetailedUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDetailedUserBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}