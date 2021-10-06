package com.example.myapplication.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.bug.BugActivity
import com.example.myapplication.presentation.company.CompanyActivity
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.mypage.MyPageActivity
import com.example.myapplication.presentation.product.ProductActivity
import com.example.myapplication.presentation.survey.SurveyActivity
import com.google.android.material.navigation.NavigationView
import org.koin.android.scope.ScopeActivity

class HomeActivity : ScopeActivity(), HomeContract.View {

    override val presenter: HomeContract.Presenter by inject()

    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    private val navigationDrawer by lazy {
        findViewById<NavigationView>(R.id.nav_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val currentUserId = presenter.setCurrentUserId()
        val currentUserName = presenter.setCurrentUserName()
        Log.d("HomeActivity", "$currentUserId $currentUserName")

        initViews(currentUserName)
        bindViews(currentUserId)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(currentUserName: String) {
        val navigationController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)

        toolBar.setupWithNavController(navigationController, drawerLayout)

        navigationDrawer.apply {
            layoutParams.width = Resources.getSystem().displayMetrics.widthPixels * 3 / 4
            inflateHeaderView(R.layout.main_menu_header).let {
                it.findViewById<TextView>(R.id.tv_profile_name).text = "$currentUserName 님"
            }
        }


        navigationDrawer.getHeaderView(0).apply {
            layoutParams.width = navigationDrawer.layoutParams.width
        }

    }


    private fun bindViews(currentUserId: String) {
        findViewById<TextView>(R.id.tv_log_out).setOnClickListener {
            navigationDrawer.visibility = View.INVISIBLE
            AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("네") { _, _ ->
                    presenter.logOutUser()
                }
                .setNegativeButton("아니오") { _, _ ->
                    navigationDrawer.visibility = View.VISIBLE
                }
                .show()
        }

        findViewById<TextView>(R.id.tv_sign_out).setOnClickListener {
            // todo 회원탈퇴
            presenter.clearUser(currentUserId)
            presenter.logOutUser()
        }

        navigationDrawer.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.companyActivity -> {
                    val intent = Intent(this, CompanyActivity::class.java)
                    startActivity(intent)
                    true
                } else -> true
            }
        }
    }

    override fun moveToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }

}