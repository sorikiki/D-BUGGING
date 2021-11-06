package com.example.myapplication.presentation.company

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeActivity

class CompanyActivity: AppCompatActivity() {
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        initViews()
        bindViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.companyListFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        toolBar.setupWithNavController(navigationController, appBarConfiguration)
    }

    private fun bindViews() {
        val toolBarSearchIcon = findViewById<ImageView>(R.id.tool_bar_search_icon)
        val toolBarHomeIcon = findViewById<ImageView>(R.id.tool_bar_home_icon)
        val cancelSearchIcon = findViewById<TextView>(R.id.cancel_search)
        val toolBarLine = findViewById<View>(R.id.tool_bar_line)

        toolBarSearchIcon.setOnClickListener {
            it.visibility = View.GONE
            cancelSearchIcon.visibility = View.VISIBLE
            toolBarLine.visibility = View.GONE

            navigationController.navigate(R.id.action_companyListFragment_to_companySearchFragment)
        }

        cancelSearchIcon.setOnClickListener {
            it.visibility = View.GONE
            toolBarSearchIcon.visibility = View.VISIBLE
            toolBarLine.visibility = View.VISIBLE

            navigationController.popBackStack()
        }

        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.companyListFragment) {
                toolBarSearchIcon.visibility = View.GONE
            } else {
                toolBarSearchIcon.visibility = View.VISIBLE
                cancelSearchIcon.visibility = View.GONE
            }
        }

        toolBarHomeIcon.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}