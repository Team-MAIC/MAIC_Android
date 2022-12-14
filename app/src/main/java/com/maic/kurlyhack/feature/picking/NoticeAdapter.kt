package com.maic.kurlyhack.feature.picking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.data.remote.response.MessageData
import com.maic.kurlyhack.databinding.ItemNoticeBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.callback
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NoticeAdapter(val onItemClick: OnItemClick) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    val noticeList = mutableListOf<MessageData>()

    class NoticeViewHolder(val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageData) {
            with(binding) {
                tvNoticePartAddress.text = data.fullLocation
                tvNotice.text = data.content
                val formatter: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.KOREA)
                formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
                val text: String = formatter.format(data.time)
                tvNoticeTime.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.onBind(noticeList[position])

        holder.binding.button.setOnClickListener {
            KurlyClient.messageService.putConfirmMessage(
                noticeList[position].messageId
            ).callback.onSuccess {
                noticeList.removeAt(position)
                notifyDataSetChanged()
                if(noticeList.size == 0) {
                    onItemClick.onClick("nothing")
                }
            }.enqueue()
        }
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }
}
