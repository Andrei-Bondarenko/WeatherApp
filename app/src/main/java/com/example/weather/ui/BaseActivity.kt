package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity : AppCompatActivity() {

    protected fun changeFragment(fragment: DefaultFragment, id: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(id, fragment)
            .commit()
    }
}