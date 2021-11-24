package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class BugList(
    @SerializedName("bug_list")
    val bugList: List<BugItem>?
)