package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(mainBinding.mainFrame.id, HomeFragment())
                .commit()
        }
    }
}

