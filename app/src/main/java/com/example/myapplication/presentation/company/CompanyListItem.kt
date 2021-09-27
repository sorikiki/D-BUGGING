package com.example.myapplication.presentation.company

class CompanyListItem(
    var name: String,
    var grade: Double,
    var intro: String
) {
    @JvmName("getName1")
    fun getName(): String {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
    }

    @JvmName("getGrade1")
    fun getGrade(): Double {
        return grade
    }

    @JvmName("setGrade1")
    fun setGrade(grade: Double) {
        this.grade = grade
    }

    @JvmName("getIntro1")
    fun getIntro(): String {
        return intro
    }

    @JvmName("setIntro1")
    fun setIntro(intro: String) {
        this.intro = intro
    }
}