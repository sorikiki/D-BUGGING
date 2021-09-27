package com.example.myapplication.presentation.company

import CompanyListAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanyListBinding
import java.util.ArrayList

class CompanyListFragment: Fragment() {
    lateinit var companyRecyclerView: RecyclerView
    lateinit var companyAdapter: CompanyListAdapter
    var companyItems: ArrayList<CompanyListItem> = ArrayList<CompanyListItem>()

    private var binding: FragmentCompanyListBinding? = null
//
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentCompanyListBinding.inflate(inflater, container, false)
        .also {
            binding = it
            companyRecyclerView= binding!!.rvCompanyList
            //리사이클러뷰에 adapter 객체 지정
            companyAdapter = CompanyListAdapter(companyItems)
            companyRecyclerView.setAdapter(companyAdapter)
            var layoutManager = LinearLayoutManager(context) // 리사이클러뷰 그리드 레이아웃
            companyRecyclerView.setLayoutManager(layoutManager)
        }
        .root

    fun addItem(name:String, grade: Double, intro:String) {
        val item = CompanyListItem(name, grade, intro)
        item.setName(name)
        item.setGrade(grade)
        item.setIntro(intro)
        companyItems.add(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}