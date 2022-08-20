package com.maic.kurlyhack.feature.picking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.local.NoticeData
import com.maic.kurlyhack.databinding.ItemNoticeBinding

class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    val noticeList = mutableListOf<NoticeData>()

    class NoticeViewHolder(val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NoticeData) {
            with(binding) {
                tvNoticePartAddress.text = data.partAddress
                tvNotice.text = data.notice
                tvNoticeTime.text = data.time
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.onBind(noticeList[position])
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }
}
