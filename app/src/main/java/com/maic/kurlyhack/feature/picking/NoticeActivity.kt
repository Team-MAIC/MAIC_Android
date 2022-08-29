package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivityNoticeBinding
import com.maic.kurlyhack.feature.help.PickingHelp1Activity
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class NoticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeBinding
    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)

        initAdapter()
        initClickListener()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        noticeAdapter = NoticeAdapter()
        binding.rvNotice.adapter = noticeAdapter

        KurlyClient.messageService.getMessage(
            1
        ).callback.onSuccess {
            noticeAdapter.noticeList.addAll(it.data!!.messages)
            noticeAdapter.notifyDataSetChanged()
        }.enqueue()
    }

    private fun initClickListener() {
        binding.ivNoticeBack.setOnClickListener {
            finish()
        }

        binding.ivNoticeMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivNoticeHelp.setOnClickListener {
            startActivity(Intent(this, PickingHelp1Activity::class.java))
        }
    }
}
