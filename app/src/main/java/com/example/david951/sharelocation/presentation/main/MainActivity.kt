package com.example.david951.sharelocation.presentation.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.david951.sharelocation.R
import com.example.david951.sharelocation.presentation.addFragment
import com.example.david951.sharelocation.presentation.findFragmentByTag
import com.example.david951.sharelocation.presentation.main.MainActivity.FragmentTags.positionFragmentTag
import com.example.david951.sharelocation.presentation.position.PositionFragment
import com.example.david951.sharelocation.presentation.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    object FragmentTags {
        const val positionFragmentTag = "PositionFragment"
        const val trackingFragmentTag = "TrackingFragment"
        const val exploreFragmentTag = "ExploreFragment"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_position -> {
                replaceFragment(findFragmentByTag(PositionFragment.newInstance() , positionFragmentTag))
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
            addFragment(PositionFragment.newInstance() , positionFragmentTag)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Timber.i("MainActivity created.")
    }
}
