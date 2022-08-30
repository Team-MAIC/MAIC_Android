package com.maic.kurlyhack.feature.picking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivityNoticeBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.help.PickingHelp1Activity
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class NoticeActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityNoticeBinding
    private lateinit var noticeAdapter: NoticeAdapter
    private var isNothing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)

        initAdapter()
        initClickListener()

        setContentView(binding.root)
    }

    private fun initAdapter() {
        noticeAdapter = NoticeAdapter(this)
        binding.rvNotice.adapter = noticeAdapter
        KurlyClient.messageService.getMessage(
            intent.getIntExtra("workerId", 0)
        ).callback.onSuccess {
            if (it.data!!.messages.size == 0) {
                Toast.makeText(this, "존재하는 알림이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                noticeAdapter.noticeList.addAll(it.data.messages)
                noticeAdapter.notifyDataSetChanged()
            }
        }.enqueue()
    }

    private fun initClickListener() {
        binding.ivNoticeBack.setOnClickListener {
            if (isNothing) {
                val intent = Intent(this@NoticeActivity, SelectPickingActivity::class.java)
                val intent2 = Intent(this@NoticeActivity, PickingActivity::class.java)
                setResult(Activity.RESULT_OK, intent)
                setResult(Activity.RESULT_OK, intent2)
            }
            finish()
        }

        binding.ivNoticeMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivNoticeHelp.setOnClickListener {
            startActivity(Intent(this, PickingHelp1Activity::class.java))
        }
    }

    override fun onClick(value: String) {
        if(value == "nothing") {
            isNothing = true
        }
    }

    override fun onListClick(value: ArrayList<String>) {
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        Toast.makeText(this, "뒤로가기 버튼을 눌러주세요.", Toast.LENGTH_SHORT).show()
    }
}
