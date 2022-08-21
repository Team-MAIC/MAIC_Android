package com.maic.kurlyhack.feature.das

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityCountErrorBinding
import com.maic.kurlyhack.util.showDrawer

class CountErrorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountErrorBinding.inflate(layoutInflater)

        getData()
        initBtnClickListener()

        setContentView(binding.root)
    }

    private fun getData() {
        binding.tvCountErrorPart.text = intent.getStringExtra("addressBox")
        binding.tvCountErrorItem.text = intent.getStringExtra("Item")
    }

    private fun initBtnClickListener() {
        binding.btnCountTransmit.setOnClickListener {
            // TODO: api 연결
            finish()
        }

        binding.ivCountErrorMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }

        binding.ivCountErrorBack.setOnClickListener {
            finish()
        }
    }
}
