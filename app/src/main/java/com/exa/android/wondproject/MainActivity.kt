package com.exa.android.wondproject

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.exa.android.wondproject.Fragments.HomeFragment
import com.exa.android.wondproject.Fragments.OtherFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var drawer: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer)
        navView = findViewById(R.id.navigation)

        setSupportActionBar(toolbar) // setting toolbar on layout

        loadFragment() // call to load home screen by default

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.openDrawable, R.string.closeDrawable
        )

        drawer.addDrawerListener(toggle) // setting navigation drawer when toggle is pressed

        toggle.syncState() // if toggle is already open then close and close then open

        // navigating fragments when different menu is selected

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment()
                }

                R.id.privacy -> {
                    loadFragment(OtherFragment(), "Privacy")
                }

                R.id.profile -> {
                    loadFragment(OtherFragment(), "Profile Screen")
                }

                R.id.about -> {
                    loadFragment(OtherFragment(), "About Us")
                }

                else -> {
                    // Show AlertDialog to confirm app exit
                    showAlert()
                }
            }
            drawer.closeDrawers()
            true // Return true to indicate the item click was handled
        }
    }

    // create a dialog box

    fun showAlert() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit app?")
            .setPositiveButton("Yes") { dialog, which ->
                // Close the app
                finish()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    // on pressing back if you are already at home then exit, if on another screen
    //then move to home and if drawer is open so close drawer

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) // for open , close drawer
            drawer.closeDrawers()
        else {// for screen
            when (supportFragmentManager.findFragmentById(R.id.container)) {
                !is HomeFragment -> loadFragment()
                else -> {
                    super.onBackPressed()
                }
            }
        }
    }

    // navigate fragments for different menu selection
    // by default parameters are for Home fragments due to using home screen a lot time
    //set its fragments and text by default

    private fun loadFragment(fragment: Fragment = HomeFragment(), title: String = "Home") {
        // calling fragment to navigate on new screen
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        // setting title for new screen
        supportActionBar?.title = title  // it can be done as : toolbar.title = ""
    }

}