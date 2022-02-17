package com.example.myapplication.presentation.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.presentation.login.LoginContract
import org.koin.android.scope.ScopeFragment

class RegisterFragment : ScopeFragment(), RegisterContract.View {
    private var binding: FragmentRegisterBinding? = null

    override val presenter: RegisterContract.Presenter by inject()

    private lateinit var name: String
    private lateinit var id: String
    private lateinit var passwordOrigin: String
    private lateinit var passwordCheck: String
    private lateinit var contactNumbers: String
    private lateinit var email: String

    private val zipCode by lazy {
        binding?.etPostAddress?.text.toString().let {
            if (it.isNotEmpty()) {
                it.toInt()
            } else {
                null
            }
        }
    }

    private lateinit var roadAddress: String
    private lateinit var detailAddress: String

    private val sizeOfHouse by lazy {
        binding?.etSpace?.text.toString().let {
            if (it.isNotEmpty()) {
                it.toDouble()
            } else {
                null
            }
        }
    }

    private val numOfRooms by lazy {
        binding?.etRoom?.text.toString().let {
            if (it.isNotEmpty()) {
                it.toInt()
            } else {
                null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRegisterBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        binding?.apply {

            var isIdChecked = false
            var isAuthenticationCompleted = false

            btnRegister.setOnClickListener {

                name = etName.text.toString()
                id = etId.text.toString()
                passwordOrigin = etPw.text.toString()
                passwordCheck = etPwCheck.text.toString()
                contactNumbers = etProfile.text.toString()
                email = etEmail.text.toString()
                roadAddress = etAddress.text.toString()
                detailAddress = etDetailAddress.text.toString()

                if (!isIdChecked) {
                    Toast.makeText(context, "아이디 중복 체크가 되지 않았습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    if (passwordOrigin != passwordCheck) {
                        Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        if (!isAuthenticationCompleted) {
                            Toast.makeText(context, "연락처 인증을 완료해주세요.", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            succeedSignUp(
                                name,
                                id,
                                passwordOrigin,
                                contactNumbers,
                                email,
                                zipCode,
                                roadAddress,
                                detailAddress,
                                sizeOfHouse,
                                numOfRooms
                            )
                        }
                    }
                }
            }

            // todo 아이디 중복 체크 => 서버에 정보 조회해서 확인하는 코드로 변경
            btnMulConfirm.setOnClickListener {
                isIdChecked = true
            }

            // todo 전화번호 인증 => 사용자에게 번호가 전송되어 해당 번호를 입력하게 해야함
            btnSendConfirm.setOnClickListener {
                isAuthenticationCompleted = true
            }

            // 우편번호 검색
            btnAddressSearch.setOnClickListener {

            }
        }
    }


    private fun succeedSignUp(
        name: String,
        id: String,
        password: String,
        contactNumbers: String,
        email: String,
        zipCode: Int? = null,
        roadAddress: String? = null,
        detailAddress: String? = null,
        sizeOfHouse: Double? = null,
        numOfRooms: Int? = null
    ) {
        presenter.signUpUser(
            UserInfo(
                id = id,
                password = password,
                name = name,
                contactNumbers = contactNumbers,
                email = email,
                zipCode = zipCode,
                roadAddr = roadAddress,
                detailAddr = detailAddress,
                sizeOfHouse = sizeOfHouse,
                numOfRooms = numOfRooms,
                accumulatedNumOfUsages = 0
            )
        )
    }

    override fun processSignUpSuccess() {
        view?.findNavController()?.navigate(R.id.action_registerFragment_to_registerFragmentCompleted)
    }

    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}