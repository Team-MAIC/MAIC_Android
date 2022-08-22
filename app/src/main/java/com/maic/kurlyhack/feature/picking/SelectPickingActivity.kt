package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.PartData
import com.maic.kurlyhack.databinding.ActivitySelectPickingBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.showDrawer

class SelectPickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivitySelectPickingBinding
    private lateinit var selectPickingAdapter: SelectPickingAdapter
    var workerId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectPickingBinding.inflate(layoutInflater)

        getData()
        initAdapter()
        initClickListener()

        setContentView(binding.root)
    }

    private fun getData() {
        workerId = intent.getIntExtra("workerId", 0)
    }

    private fun initAdapter() {
        selectPickingAdapter = SelectPickingAdapter(this)

        binding.rvSelectPickingPart.adapter = selectPickingAdapter

        selectPickingAdapter.partList.addAll(
            listOf(
                PartData("1회차", true),
                PartData("2회차", false),
                PartData("3회차", false)
            )
        )
        selectPickingAdapter.notifyDataSetChanged()
    }

    private fun initClickListener() {
        binding.ivSelectPickingNotice.setOnClickListener {
            startActivity(Intent(this@SelectPickingActivity, NoticeActivity::class.java))
        }
        binding.ivSelectPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    override fun onClick(value: String) {
        val intent = Intent(this, PickingActivity::class.java)
        intent.putExtra("pickingPart", value)
        intent.putExtra("workerId", workerId)
        startActivity(intent)
    }

    override fun onListClick(value: ArrayList<String>) {
    }
}
