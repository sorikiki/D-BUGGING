package com.example.myapplication.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeActivity
import com.example.myapplication.presentation.register.RegisterActivity

import org.koin.android.scope.ScopeActivity

class LoginActivity: ScopeActivity(), LoginContract.View {

    override val presenter: LoginContract.Presenter by inject()

    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.progress_bar)
    }

    private val logInContainer by lazy {
        findViewById<View>(R.id.login_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onViewCreated()

        bindViews()
    }

    private fun bindViews() {
        val logInButton = findViewById<Button>(R.id.btn_login)
        logInButton.setOnClickListener {
            getUserInput()
        }

        val registerButton = findViewById<Button>(R.id.btn_register)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getUserInput() {
        val id = findViewById<EditText>(R.id.login_id).text.toString()
        val password = findViewById<EditText>(R.id.login_pw).text.toString()

        if (id == null || password == null) {
            Toast.makeText(this, "정보가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
        }

        presenter.signInUser(id, password)
    }

    override fun processLoginSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun processLoginFail() {
        Toast.makeText(this, "로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
        logInContainer.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
        logInContainer.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }
}