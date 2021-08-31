package com.example.myapplication.presentation.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R

class RegisterActivity: AppCompatActivity() {
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        bindViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolBar)
        setupActionBarWithNavController(navigationController)
    }

    private fun bindViews() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            title = if (destination.id == R.id.registerFragment) {
                resources.getString(R.string.sign_up_string)
            } else {
                resources.getString(R.string.main_title)
            }
        }
    }
}