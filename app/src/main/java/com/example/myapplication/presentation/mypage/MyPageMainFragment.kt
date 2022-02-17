package com.example.myapplication.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMypageMainBinding
import com.example.myapplication.domain.UserInformation
import com.example.myapplication.ext.convertProcessStateToString
import com.example.myapplication.ext.dateTimeFormatter
import com.example.myapplication.ext.newDateFormatter
import org.koin.android.scope.ScopeFragment

class MyPageMainFragment: ScopeFragment(), MyPageMainContract.View
{
    private var binding: FragmentMypageMainBinding? = null

    lateinit var userInformation: UserInformation

    override val presenter: MyPageMainContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMypageMainBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        presenter.getUserInformation()
    }

    private fun bindViews() {
        binding?.apply {
            tvMoreSurvey.setOnClickListener {
                val bundle = bundleOf("surveyList" to userInformation.surveyList, "userName" to userInformation.userName)
                findNavController().navigate(R.id.action_myPageMainFragment_to_myPageSurveyDetailListFragment, bundle)
            }

            tvMoreProduct.setOnClickListener {
                val bundle = bundleOf("productList" to userInformation.productList, "userName" to userInformation.userName)
                findNavController().navigate(R.id.action_myPageMainFragment_to_myPageProductInterestListFragment, bundle)
            }

            tvMoreCompany.setOnClickListener {
                val bundle = bundleOf("reservationList" to userInformation.reservationList, "userInfo" to userInformation)
                findNavController().navigate(R.id.action_myPageMainFragment_to_myPageCompanyDetailListFragment, bundle)
            }
        }
    }

    override fun showUserInformation(userInfo: UserInformation?) {
        if (userInfo != null) {
            userInformation = userInfo
        }

        binding?.apply {
            tvName.text = userInfo?.userName.plus(" 님")
            tvUsecompany.text = "누적 업체 이용 ".plus(userInfo?.accumulatedNumOfUsages).plus("건")
            tvAddress.text = userInfo?.roadAddress.plus(" " + userInfo?.detailAddress).plus(" ").plus(userInfo?.zipCode)
            tvArea.text = userInfo?.sizeOfHouse.toString().plus("평")
            tvCompany.text = userInfo?.numOfInterestedCompanies.toString().plus("개")

            tvBugName1.text = userInfo?.surveyList?.get(0)?.bugName
            tvDate1.text = newDateFormatter.format(dateTimeFormatter.parse(userInfo?.surveyList?.get(0)?.surveyDate!!)!!)
            tvBugName2.text = userInfo.surveyList[1].bugName
            tvDate2.text = newDateFormatter.format(dateTimeFormatter.parse(userInfo.surveyList[1].surveyDate!!)!!)

            tvProductName1.text = userInfo.productList?.get(0)?.productName
            tvProductName2.text = userInfo.productList?.get(1)?.productName

            tvCompanyName1.text = userInfo.reservationList?.get(0)?.companyName
            tvProcessState1.text = userInfo.reservationList?.get(0)?.convertProcessStateToString()
            tvCompanyName2.text = userInfo.reservationList?.get(1)?.companyName
            tvProcessState2.text = userInfo.reservationList?.get(1)?.convertProcessStateToString()
        }
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.VISIBLE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}