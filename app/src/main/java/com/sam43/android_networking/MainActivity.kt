package com.sam43.android_networking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sam43.android_networking.dagger_test.Car
import com.sam43.android_networking.dagger_test.DaggerCarComponents
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var car: Car

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
        //var components: CarComponents =
        DaggerCarComponents.create().inject(this)
        toast(car.drive())
    }

    private fun setupNavController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /**
         * // Shared viewmodel experiment here...
         *
         * if (lettersViewModel.hasFullProfile()) {
        toSend()
        findNavController().popBackStack(R.id.inboxFragment, false)  //1
        findNavController().navigate(R.id.sentFragment)  //2
        } else {
        findNavController().navigate(R.id.editProfileFragment)  //3
        }
         * */
    }

}
