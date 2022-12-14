package com.maic.kurlyhack.feature.picking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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
        checkNotice()

        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1000 -> {
                    binding.ivSelectPickingNoticeYes.visibility = View.INVISIBLE
                }
            }
        }
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
            val intent = Intent(this@SelectPickingActivity, NoticeActivity::class.java)
            intent.putExtra("workerId", workerId)
            startActivityForResult(intent, 1000)
        }

        binding.ivSelectPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivSelectPickingRefresh.setOnClickListener {
            initAdapter()
        }

        binding.ivSelectPickingHelp.setOnClickListener {
            startActivity(Intent(this, PickingHelp1Activity::class.java))
        }
    }

    private fun checkNotice() {
        KurlyClient.messageService.getMessage(
            workerId
        ).callback.onSuccess {
            if (it.data!!.messages.size == 0) {
                binding.ivSelectPickingNoticeYes.visibility = View.INVISIBLE
            } else {
                binding.ivSelectPickingNoticeYes.visibility = View.VISIBLE
            }
        }.enqueue()
    }

    override fun onClick(value: String) {
    }

    override fun onListClick(value: ArrayList<String>) {
        val intent = Intent(this, PickingActivity::class.java)
        intent.putExtra("roundId", value[0].toInt())
        intent.putExtra("centerRoundNumber", value[1])
        intent.putExtra("area", area)
        intent.putExtra("workerId", workerId)
        startActivityForResult(intent, 1000)
    }
}
