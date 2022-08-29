package com.maic.kurlyhack.feature.picking

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maic.kurlyhack.databinding.ActivityItemBinding
import com.maic.kurlyhack.feature.help.DasHelp1Activity
import com.maic.kurlyhack.feature.help.DasHelp8Activity
import com.maic.kurlyhack.feature.help.PickingHelp1Activity
import com.maic.kurlyhack.util.showDrawer

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)

        getData()
        initBtnClickListener()

        setContentView(binding.root)
    }

    private fun getData() {
        with(binding) {
            tvItemErrorDes.text = "오류: " + intent.getStringExtra("codeItem")
            if (intent.getBooleanExtra("isSuccess", false)) {
                tvItemError.visibility = View.INVISIBLE
                tvItemErrorDes.visibility = View.INVISIBLE
            }
            tvPartAddress.text = intent.getStringExtra("partAddress")
            tvItemName.text = intent.getStringExtra("item")
            Glide.with(this@ItemActivity).load("https://img.project-maic.com/product/" + intent.getStringExtra("picture")).into(binding.ivItem)
        }
    }

    private fun initBtnClickListener() {
        binding.ivListBack.setOnClickListener {
            finish()
        }

        binding.ivItemMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivItemHelp.setOnClickListener {
            startActivity(Intent(this, PickingHelp1Activity::class.java))
        }
    }
}
