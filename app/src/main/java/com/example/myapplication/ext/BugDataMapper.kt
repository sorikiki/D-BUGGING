package com.example.myapplication.ext

import android.content.Context
import android.util.TypedValue
import com.example.myapplication.R
import com.example.myapplication.domain.BugInformation

fun BugInformation.toDrawable(): Int {
    return when (bugId) {
        // 좀벌레
        1 -> R.drawable.ic_silverfish
        // 먼지다듬이
        2 -> R.drawable.ic_dust
        // 바퀴벌레
        3 -> R.drawable.ic_cockroach
        // 화랑곡나방
        4 -> R.drawable.ic_moth
        // 쌀바구미
        5 -> R.drawable.ic_ssal
        // 그리마
        6 -> R.drawable.ic_millipede
        // 빈대
        7 -> R.drawable.ic_bedbug
        // 모기
        else -> R.drawable.ic_mosquito
    }
}

fun BugInformation.setDrawablePadding(): Float {
    return when (bugId) {
        // 좀벌레
        1 -> 10F
        // 먼지다듬이
        2 -> 12F
        // 바퀴벌레
        3 -> 16F
        // 화랑곡나방
        4 -> 16F
        // 쌀바구미
        5 -> 12F
        // 그리마
        6 -> 14F
        // 빈대
        7 -> 16F
        // 모기
        else -> 14F
    }
}



