package com.example.androidlibrary.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.androidlibrary.R
import com.example.androidlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        binding.buttonCounterFirst.setOnClickListener {listener}
        binding.buttonCounterSecond.setOnClickListener {listener}
        binding.buttonCounterThird.setOnClickListener {listener}
    }


    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.buttonCounterFirst.text = text
            1 -> binding.buttonCounterSecond.text = text
            2 -> binding.buttonCounterThird.text = text
        }
    }
}