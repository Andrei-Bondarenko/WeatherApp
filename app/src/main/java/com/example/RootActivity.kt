package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.weather.R
import com.example.weather.start_page.ui.DefaultFragment

class RootActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)
            setContentView(R.layout.activity_main)
            replace(DefaultFragment(), R.id.emptyContainer)
        }
        private fun replace(fragment: Fragment, id: Int) {
            val fragmentManager = this.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
                .replace(id, fragment)
                .commit()
        }
}