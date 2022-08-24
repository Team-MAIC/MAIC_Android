package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivitySelectPickingBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.feature.help.PickingHelp1Activity
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class SelectPickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivitySelectPickingBinding
    private lateinit var selectPickingAdapter: SelectPickingAdapter
    var workerId = 0
    var area = ""

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
        area = intent.getStringExtra("workerArea").toString()
        Log.d("###", area)
    }

    private fun initAdapter() {

        KurlyClient.pickingService.getRoundsData(
            workerId
        ).callback.onSuccess {
            selectPickingAdapter = SelectPickingAdapter(this)

            binding.rvSelectPickingPart.adapter = selectPickingAdapter

            selectPickingAdapter.partList.addAll(it.data!!.rounds)
            selectPickingAdapter.notifyDataSetChanged()
        }.enqueue()
    }

    private fun initClickListener() {
        binding.ivSelectPickingNotice.setOnClickListener {
            startActivity(Intent(this@SelectPickingActivity, NoticeActivity::class.java))
        }
        binding.ivSelectPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
        binding.ivSelectPickingRefresh.setOnClickListener {
            initAdapter()
        }
    }

    override fun onClick(value: String) {
    }

    override fun onListClick(value: ArrayList<String>) {
        val intent = Intent(this, PickingActivity::class.java)
        intent.putExtra("roundId", value[0].toInt())
        intent.putExtra("centerRoundNumber", value[1])
        intent.putExtra("area", area)
        intent.putExtra("workerId", workerId)
        startActivity(intent)
    }
}
