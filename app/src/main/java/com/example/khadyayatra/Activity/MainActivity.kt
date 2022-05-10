package com.example.khadyayatra.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.khadyayatra.*
import com.example.khadyayatra.Fragment.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerlayout : DrawerLayout
    lateinit var coordinatorlayout : CoordinatorLayout
    lateinit var frame_Layout : FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    var previousMenuItem : MenuItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerlayout = findViewById(R.id.drawer_layout)
        coordinatorlayout = findViewById(R.id.coordinator_layout)
        frame_Layout = findViewById(R.id.frame_layout)
        navigationView = findViewById(R.id.navigation_view)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerlayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerlayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {

            if (previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it


            when (it.itemId){
                R.id.menuhome ->{
                    openDashboard()
                    drawerlayout.closeDrawers()
                }
                R.id.menuuserprofile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, UserProfileFragment())
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawerlayout.closeDrawers()
                }
                R.id.menufavourite ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavouriteRestoFragment())
                        .commit()
                    supportActionBar?.title = "Favourite Restaurants"
                    drawerlayout.closeDrawers()
                }
                R.id.menuhistory ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, OrderHistoryFragment())
                        .commit()
                    supportActionBar?.title = "Order History"
                    drawerlayout.closeDrawers()
                }
                R.id.menufaq ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FaqFragment())
                        .commit()
                    supportActionBar?.title = "Frequently Asked Questions"
                    drawerlayout.closeDrawers()
                }
                R.id.menulogout ->{
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        //supportActionBar?.title = "Khadya Yatra"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == android.R.id.home){
            drawerlayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment).addToBackStack("menuhome")
            .commit()
        supportActionBar?.title = "Home"
        navigationView.setCheckedItem(R.id.menuhome)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame_layout)
        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
}