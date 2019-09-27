package com.sam43.android_networking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sam43.android_networking.ui.ParentFragment
import com.sam43.android_networking.utils.loadFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this.loadFragment(R.id.container, parentFragment)
        setupNavController()
    }

    private fun setupNavController() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        NavigationUI.setupWithNavController(
            nav_view, navController
        )
        nav_view.selectedItemId = R.id.navigation_home
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    //call.showToast("this is home")
                    Log.d("bottomNav","Home")
                    toast("home")
                }
                R.id.navigation_dashboard -> toast("dashboard")
                R.id.navigation_notifications -> toast("notifications")
            }
            NavigationUI.onNavDestinationSelected(it, navController)
        }
        //nav_view.setupWithNavController(navController)
    }
}
