package com.skydivers.hotelstest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.skydivers.hotelstest.databinding.ActivityMainBinding
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProvider
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private  var _binding: ActivityMainBinding?=null
    private val binding:ActivityMainBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: MaterialToolbar
    private val mainNavControllerProvider: MainNavControllerProvider by inject()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = findViewById(R.id.customToolBar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_hotel)
        )





        toolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )


        navController.addOnDestinationChangedListener{_, destination, _ ->

            if (destination.id != R.id.navigation_hotel)
                toolbar.setNavigationIcon(R.drawable.baseline_keyboard_arrow_left)

                toolbar.elevation = 0f



        }



    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()
        mainNavControllerProvider.bindNavController(navController)
    }

    override fun onPause() {
        super.onPause()
        mainNavControllerProvider.unBindNavController()
    }
}