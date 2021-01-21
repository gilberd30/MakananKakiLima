package com.aplikasi.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aplikasi.myapplication.fragment.FragmentAccount
import com.aplikasi.myapplication.fragment.FragmentHome
import com.aplikasi.myapplication.fragment.FragmentProduct
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom.setOnNavigationItemSelectedListener(bottomNavi)
        addFragment(FragmentHome())
    }

    private val bottomNavi = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when(item.itemId){
            R.id.home->{
                addFragment(FragmentHome())
                return@OnNavigationItemSelectedListener true
            }
            R.id.product->{
                addFragment(FragmentProduct())
                return@OnNavigationItemSelectedListener true
            }
            R.id.account->{
                addFragment(FragmentAccount())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(frg: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmennt, frg)
            .commit()
    }
}