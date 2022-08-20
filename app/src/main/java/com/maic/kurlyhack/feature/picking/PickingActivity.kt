package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.PickingData
import com.maic.kurlyhack.databinding.ActivityPickingBinding

class PickingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingBinding
    private lateinit var pickingAdapter: PickingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBinding.inflate(layoutInflater)

        getData()
        initAdapter()
        initClickListener()

        setContentView(binding.root)
    }

    private fun getData() {
        binding.tvPickingPart.text = intent.getStringExtra("pickingPart")
    }

    private fun initAdapter() {
        pickingAdapter = PickingAdapter()

        binding.rvPicking.adapter = pickingAdapter

        pickingAdapter.pickingList.addAll(
            listOf(
                PickingData("B0-01-01", "천도복숭아", "3개"),
                PickingData("B0-01-03", "수박", "6개"),
                PickingData("B0-01-03", "사과", "11개"),
                PickingData("B0-03-02", "치약", "7개"),
                PickingData("B0-01-02", "멜론", "1개"),
                PickingData("B0-01-01", "천도복숭아", "3개"),
                PickingData("B0-01-01", "수박", "6개"),
                PickingData("B0-01-01", "사과", "11개"),
                PickingData("B0-01-01", "치약", "7개"),
                PickingData("B0-01-01", "멜론", "1개")
            )
        )
        pickingAdapter.notifyDataSetChanged()
    }

    private fun initClickListener() {
        binding.ivPickingNotice.setOnClickListener {
            startActivity(Intent(this@PickingActivity, NoticeActivity::class.java))
        }
    }
}
