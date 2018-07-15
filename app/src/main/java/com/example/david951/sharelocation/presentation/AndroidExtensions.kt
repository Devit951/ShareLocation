package com.example.david951.sharelocation.presentation

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.david951.sharelocation.R

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment , @IdRes containerId: Int = R.id.fragment_container){
    supportFragmentManager.replaceFragment(fragment , containerId)
}

fun AppCompatActivity.addFragment(fragment: Fragment , tag: String , @IdRes containerId: Int = R.id.fragment_container){
    supportFragmentManager.addFragment(fragment , tag , containerId)
}

fun FragmentManager.addFragment(fragment: Fragment, tag: String , @IdRes containerId: Int){
    inTransaction { add(containerId , fragment , tag) }
}

fun FragmentManager.replaceFragment(fragment: Fragment , @IdRes containerId: Int){
    inTransaction { replace(containerId , fragment) }
}

fun AppCompatActivity.findFragmentByTag(fragment: Fragment, tag: String) : Fragment {
    if (supportFragmentManager.findFragmentByTag(tag) == null){
        addFragment(fragment , tag)
    }
    return supportFragmentManager.findFragmentByTag(tag)
}