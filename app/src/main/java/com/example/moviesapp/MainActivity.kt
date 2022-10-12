package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.utils.Constants.Companion.API_KEY
import com.example.moviesapp.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        changeFragment(HomeFragment())
    }

    fun changeFragment(fragmentToChange: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(mainBinding.mainFrame.id, fragmentToChange)
            addToBackStack(null)
            commit()
        }
    }
}

