package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class CompanyList(
    @SerializedName("company_list")
    val companyList: List<CompanyItem>?
)
