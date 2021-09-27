package com.example.myapplication.presentation.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.presentation.home.HomeActivity
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.login.LoginContract
import org.koin.android.ext.android.inject

class RegisterActivity: AppCompatActivity(), RegisterContract.View {

    override val presenter: RegisterContract.Presenter by inject()

    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        bindViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        toolBar.setupWithNavController(navigationController, appBarConfiguration)
    }

    private fun bindViews() {
        val registerBtn = findViewById<Button>(R.id.btn_register);
        registerBtn.setOnClickListener {
            putUserInput()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun putUserInput() {
        val name = findViewById<EditText>(R.id.et_name).text.toString()
        val id = findViewById<EditText>(R.id.et_id).text.toString()
        val password = findViewById<EditText>(R.id.et_pw).text.toString()
        val contactNumbers = findViewById<EditText>(R.id.et_profile).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val zipCode = findViewById<EditText>(R.id.et_post_address).inputType
        val roadAddr = findViewById<EditText>(R.id.et_address).text.toString()
        val detailAddr = findViewById<EditText>(R.id.et_detail_address).text.toString()
        val sizeOfHouse = findViewById<EditText>(R.id.et_space).text.toString().toDouble()
        val numOfRooms = findViewById<EditText>(R.id.et_room).inputType

        presenter.signUpUser(UserInfo(name, id, password, contactNumbers, email, zipCode, roadAddr, detailAddr, sizeOfHouse, numOfRooms))
    }

    override fun processRegisterSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun processRegisterFail() {
        Toast.makeText(this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyView()
    }

}