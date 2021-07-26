package com.example.androidassignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.androidassignment.*
import com.example.androidassignment.ui.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.drawer_toolbar.*

class SecondActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var homeFragment: HomeFragment;
    lateinit var firstFragment: FirstFragment
    lateinit var secondFragment: SecondFragment
    lateinit var thirdFragment: ThirdFragment
    lateinit var fourFragment: FourFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        init();

    }

    private fun init() {
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar;
        actionBar?.title = "Second Activity"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            (R.string.open),
            (R.string.close)
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true;
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                homeFragment = HomeFragment()
                toolBar.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.user_details -> {
                firstFragment = FirstFragment()
                toolBar.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, firstFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.user_contacts -> {
                secondFragment = SecondFragment()
                toolBar.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.design_default_color_primary
                    )
                )
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, secondFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.dummy_login -> {
                thirdFragment = ThirdFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, thirdFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.edit_user -> {
                fourFragment = FourFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, fourFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}