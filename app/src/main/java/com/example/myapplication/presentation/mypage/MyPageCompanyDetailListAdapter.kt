package com.example.myapplication.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemMypageReservationDetailBinding
import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.ext.dateTimeFormatter
import com.example.myapplication.ext.newDateTimeFormatter

class MyPageCompanyDetailListAdapter(private val onClick: (ReservationInformation) -> Unit) :
    ListAdapter<ReservationInformation, MyPageCompanyDetailListAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageCompanyDetailListAdapter.ViewHolder = ViewHolder(
        ItemMypageReservationDetailBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: MyPageCompanyDetailListAdapter.ViewHolder,
        position: Int
    ) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemMypageReservationDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReservationInformation) {
            binding.apply {
                tvCompanyName.text = item.companyName
                tvBugType.text = item.bugType

                tvProcessState.text = when (item.processState) {
                    0 -> "예약 접수"
                    1 -> "업체 확인중"
                    2 -> "방문예약 접수완료"
                    else -> "케어 종료"
                }

                tvDateTitle.text = when (item.processState) {
                    0 -> "예약 일자"
                    1 -> "방문 예정일"
                    2 -> "방문 예정일"
                    else -> "방문 일자"
                }

                tvDate.text = when (item.processState) {
                    0 -> newDateTimeFormatter.format(
                        dateTimeFormatter.parse(item.reserveDateTime!!)!!
                    )

                    else
                    -> newDateTimeFormatter.format(
                        dateTimeFormatter.parse(item.visitDateTime!!)!!
                    )
                }

                titleContainer.setImageResource(
                    when (item.processState) {
                        3 -> R.drawable.bg_mypage_reservation_title_gray_bg
                        else -> R.drawable.bg_mypage_reservation_title_bg
                    }
                )

                ivIcon.setImageResource(
                    when (item.processState) {
                        0 -> R.drawable.ic_clipboard
                        1 -> R.drawable.ic_loader
                        2 -> R.drawable.ic_calendar
                        else -> R.drawable.ic_check_circle_white
                    }
                )

                root.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ReservationInformation>() {
            override fun areItemsTheSame(
                oldItem: ReservationInformation,
                newItem: ReservationInformation
            ): Boolean {
                return oldItem.reservationId == newItem.reservationId
            }

            override fun areContentsTheSame(
                oldItem: ReservationInformation,
                newItem: ReservationInformation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}