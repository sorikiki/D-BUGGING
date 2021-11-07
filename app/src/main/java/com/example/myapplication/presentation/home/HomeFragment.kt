package com.example.myapplication.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.presentation.bug.BugActivity
import com.example.myapplication.presentation.company.CompanyActivity
import com.example.myapplication.presentation.product.ProductActivity

class HomeFragment: Fragment() {
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        binding?.apply {
            reservationMenu.setOnClickListener {
                val intent = Intent(context, CompanyActivity::class.java)
                startActivity(intent)
            }

            productMenu.setOnClickListener {
                val intent = Intent(context, ProductActivity::class.java)
                startActivity(intent)
            }

            bugMenu.setOnClickListener {
                val intent = Intent(context, BugActivity::class.java)
                startActivity(intent)
            }

        }
    }
}