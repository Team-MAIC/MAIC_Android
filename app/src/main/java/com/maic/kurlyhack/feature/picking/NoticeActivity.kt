package com.maic.kurlyhack.feature.picking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.NoticeData
import com.maic.kurlyhack.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeBinding
    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        noticeAdapter = NoticeAdapter()

        binding.rvNotice.adapter = noticeAdapter

        noticeAdapter.noticeList.addAll(
            listOf(
                NoticeData("419회차 B0-01-01", "8번 통로 천도복숭아 3개 부족", "12분전"),
                NoticeData("419회차 B0-03-01", "1번 통로 치약 1개 부족", "2분전")
            )
        )
        noticeAdapter.notifyDataSetChanged()
    }
}
