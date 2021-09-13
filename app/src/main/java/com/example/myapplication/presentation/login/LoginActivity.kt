package com.example.myapplication.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.presentation.register.RegisterActivity
import org.koin.android.ext.android.inject

class LoginActivity: AppCompatActivity(), LoginContract.View {

    override val presenter: LoginContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            finish()
        }
    }

    override fun getUserInput(): Pair<String, String> {
        val id = findViewById<EditText>(R.id.login_id).text.toString()
        val password = findViewById<EditText>(R.id.login_pw).text.toString()

        return Pair(id, password);
    }





}