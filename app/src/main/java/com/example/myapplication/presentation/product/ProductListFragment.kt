package com.example.myapplication.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentProductListBinding
import java.util.ArrayList

class ProductListFragment: Fragment() {
    lateinit var productRecyclerView: RecyclerView
    lateinit var productAdapter: ProductListAdapter
    var productItems: ArrayList<ProductListItem> = ArrayList<ProductListItem>()
    private var binding: FragmentProductListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentProductListBinding.inflate(inflater, container, false)
        .also { binding = it
            productRecyclerView= binding!!.rvProductList
            //리사이클러뷰에 adapter 객체 지정
            productAdapter = ProductListAdapter(productItems)
            productRecyclerView.setAdapter(productAdapter)
            var layoutManager = LinearLayoutManager(context) // 리사이클러뷰 그리드 레이아웃
            productRecyclerView.setLayoutManager(layoutManager)}
        .root

    fun addItem(thumb: String, name:String, intro:String) {
        val item = ProductListItem(thumb, name, intro)
        item.setThumb(thumb)
        item.setName(name)
        item.setIntro(intro)
        productItems.add(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}