package com.example.myapplication.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeActivity

class MyPageActivity: AppCompatActivity() {

    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        initViews()
        bindViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.myPageMainFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        toolBar.setupWithNavController(navigationController, appBarConfiguration)
    }

    private fun bindViews() {
        val toolBarHomeIcon = findViewById<ImageView>(R.id.tool_bar_home_icon)

        toolBarHomeIcon.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}