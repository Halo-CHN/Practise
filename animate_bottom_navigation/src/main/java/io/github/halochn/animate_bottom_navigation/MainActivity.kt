package io.github.halochn.animate_bottom_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_icon_layout.*
import kotlinx.android.synthetic.main.contact_icon_layout.*
import kotlinx.android.synthetic.main.explore_icon_layout.*
import kotlinx.android.synthetic.main.message_icon_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val destinationMap = mapOf(
            R.id.messageFragment to messageMotionLayout,
            R.id.contactFragment to contactMotionLayout,
            R.id.exploreFragment to exploreMotionLayout,
            R.id.accountFragment to accountMotionLayout
        )
        navController = findNavController(R.id.fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(destinationMap.keys))
        destinationMap.forEach { des ->
            des.value.setOnClickListener {
                navController.navigate(des.key)
            }
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0f }
            destinationMap[destination.id]?.transitionToEnd()
        }
    }
}