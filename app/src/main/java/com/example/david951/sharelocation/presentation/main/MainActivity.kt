package com.example.david951.sharelocation.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.david951.sharelocation.R
import com.example.david951.sharelocation.position.PositionFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_position -> {
                replaceFragment(PositionFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tracking -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            replaceFragment(PositionFragment.newInstance())
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Timber.i("MainActivity created.")
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }
}
