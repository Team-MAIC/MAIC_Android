package com.maic.kurlyhack.feature.picking

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityItemBinding
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
            if (intent.getBooleanExtra("isSuccess", false)) {
                tvItemError.visibility = View.INVISIBLE
                tvItemErrorDes.visibility = View.INVISIBLE
            }
            tvPartAddress.text = intent.getStringExtra("partAddress")
            tvItemName.text = intent.getStringExtra("item")
        }
    }

    private fun initBtnClickListener() {
        binding.ivListBack.setOnClickListener {
            finish()
        }

        binding.ivItemMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }
}
