package com.example.myapplication.presentation.home

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R

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

        toolBar.setupWithNavController(navigationController, drawerLayout)
    }
}