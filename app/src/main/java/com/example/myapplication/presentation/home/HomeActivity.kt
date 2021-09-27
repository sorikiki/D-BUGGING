package com.example.myapplication.presentation.home

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginTop
import androidx.core.view.size
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.google.android.material.navigation.NavigationView

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        val navigationController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        var navigationDrawer = findViewById<NavigationView>(R.id.nav_view)

        toolBar.setupWithNavController(navigationController, drawerLayout)

        navigationDrawer.apply {
            layoutParams.width = Resources.getSystem().displayMetrics.widthPixels * 3 / 4
            inflateHeaderView(R.layout.main_menu_header)
        }


        navigationDrawer.getHeaderView(0).apply {
            layoutParams.width = navigationDrawer.layoutParams.width
        }

    }
}