/*
 * Project : android-networking
 * Developed by Saadat Sayem on 1/21/20 1:51 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 1/21/20 1:51 AM
 */
package com.sam43.android_networking.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sam43.android_networking.R
import kotlinx.android.synthetic.main.activity_nav_test_main2.*
import kotlinx.android.synthetic.main.content_main.*

class BottomNavTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_test_main2)
        setSupportActionBar(bottomAppBar)
        initListeners()
    }

    private fun initListeners() {
        switchScreenButton.setOnClickListener {
            fab.hide(object : FloatingActionButton.OnVisibilityChangedListener() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onHidden(fab: FloatingActionButton?) {
                    super.onHidden(fab)
                    textView.text = "Secondary screen"
                    bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    bottomAppBar.replaceMenu(R.menu.menu_secondary)
                    fab?.setImageDrawable(getDrawable(R.drawable.ic_home_black_24dp))
                    fab?.show()
                }
            })
            bottomAppBar.navigationIcon = null
        }

        toggleFabButton.setOnClickListener { toggleFabMode() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> toast("Menu clicked!")
            R.id.navigation_home -> toast("Search clicked!")
            R.id.navigation_dashboard -> toast("dash clicked!")
            R.id.navigation_notifications -> toast("noti clicked!")
        }
        return true
    }

    private fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    /**
     * method to toggle fab mode
     *
     * @param view
     */
    private fun toggleFabMode() {
        //check the fab alignment mode and toggle accordingly
        if (bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        } else {
            bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        }
    }
}