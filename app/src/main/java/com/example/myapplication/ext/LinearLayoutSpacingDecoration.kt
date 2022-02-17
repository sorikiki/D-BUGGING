package com.example.myapplication.ext

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearLayoutSpacingDecoration : RecyclerView.ItemDecoration() {
    private val halfSpace = 48;

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.clipToPadding = true
        outRect.bottom = halfSpace
    }
}