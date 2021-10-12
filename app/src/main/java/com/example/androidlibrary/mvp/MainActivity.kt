package com.example.androidlibrary.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewButtons()
    }

    private fun initViewButtons() {
        binding.buttonCounterFirst.setOnClickListener { presenter.counterClick(CounterPosition.FIRST.position) }
        binding.buttonCounterSecond.setOnClickListener { presenter.counterClick(CounterPosition.SECOND.position) }
        binding.buttonCounterThird.setOnClickListener { presenter.counterClick(CounterPosition.THIRD.position) }
    }

    override fun setButtonFirstText(text: String) {
        binding.buttonCounterFirst.text = text
    }

    override fun setButtonSecondText(text: String) {
        binding.buttonCounterSecond.text = text
    }

    override fun setButtonThirdText(text: String) {
        binding.buttonCounterThird.text = text
    }
}