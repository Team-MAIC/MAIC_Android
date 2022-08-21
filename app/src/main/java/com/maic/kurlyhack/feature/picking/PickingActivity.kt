package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.data.local.PickingData
import com.maic.kurlyhack.databinding.ActivityPickingBinding
import com.maic.kurlyhack.feature.OnItemClick
import com.maic.kurlyhack.util.showDrawer

class PickingActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivityPickingBinding
    private lateinit var pickingAdapter: PickingAdapter
    private var pickingPart = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingBinding.inflate(layoutInflater)

        getData()
        initAdapter()
        initClickListener()
        initCategory()

        setContentView(binding.root)
    }

    private fun getData() {
        pickingPart = intent.getStringExtra("pickingPart").toString()
        binding.tvPickingPart.text = pickingPart
    }

    private fun initAdapter() {
        pickingAdapter = PickingAdapter(this)

        binding.rvPicking.adapter = pickingAdapter

        pickingAdapter.pickingList.addAll(
            listOf(
                PickingData("B0-01-01", "천도복숭아", "3개", false),
                PickingData("B0-01-03", "수박", "6개", true),
                PickingData("B0-01-03", "사과", "11개", true),
                PickingData("B0-03-02", "치약", "7개", true),
                PickingData("B0-01-02", "멜론", "1개", false),
                PickingData("B0-01-01", "천도복숭아", "3개", false),
                PickingData("B0-01-01", "수박", "6개", false),
                PickingData("B0-01-01", "사과", "11개", true),
                PickingData("B0-01-01", "치약", "7개", false),
                PickingData("B0-01-01", "멜론", "1개", true)
            )
        )
        pickingAdapter.notifyDataSetChanged()
    }

    private fun initClickListener() {
        binding.ivPickingNotice.setOnClickListener {
            startActivity(Intent(this@PickingActivity, NoticeActivity::class.java))
        }

        binding.ivPickingMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    private fun initCategory() {
        with(binding) {
            ivPickingOngoing.isSelected = true

            tvPickingAll.setOnClickListener {
                ivPickingAll.isSelected = true
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = false
            }
            tvPickingOngoing.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = true
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = false
            }
            tvPickingComplete.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = true
                ivPickingCompleteMine.isSelected = false
            }
            tvPickingCompleteMine.setOnClickListener {
                ivPickingAll.isSelected = false
                ivPickingOngoing.isSelected = false
                ivPickingComplete.isSelected = false
                ivPickingCompleteMine.isSelected = true
            }
        }
    }

    override fun onClick(value: String) {
    }

    override fun onListClick(value: ArrayList<String>) {
        if (value[3] == "code") {
            val intent = Intent(this, PickingBarCodeActivity::class.java)
            intent.putExtra("pickingInfo", value)
            intent.putExtra("pickingPart", pickingPart)
            startActivity(intent)
        } else {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("isSuccess", true)
            intent.putExtra("partAddress", pickingPart + " " + value[0])
            intent.putExtra("item", value[1] + " " + value[2])
            startActivity(intent)
        }
    }
}
