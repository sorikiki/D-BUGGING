package com.example.myapplication.presentation.product

class ProductListItem (
    var thumb:String,
    var name: String,
    var intro: String
) {

    @JvmName("getThumb1")
    fun getThumb(): String {
        return thumb
    }

    @JvmName("setThumb1")
    fun setThumb(thumb: String) {
        this.thumb = thumb
    }

    @JvmName("getName1")
    fun getName(): String {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
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
