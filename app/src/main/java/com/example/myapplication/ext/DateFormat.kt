package com.example.myapplication.ext

import java.text.SimpleDateFormat
import java.util.*

val dateTimeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    .also {
        it.timeZone = TimeZone.getTimeZone("GMT")
    }

val newDateTimeFormatter = SimpleDateFormat("yyyy'년' MM'월' dd'일' HH:mma", Locale.US)
val newDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)