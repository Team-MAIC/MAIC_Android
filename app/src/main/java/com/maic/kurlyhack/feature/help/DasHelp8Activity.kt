package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp8Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp8Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_das_help8)
    }

    private fun initEventListener() {
        binding.btnDasBarcode.setOnClickListener {
            finish()
        }

        binding.clDasHelp8.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp8Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp8Activity, DasHelp7Activity::class.java))
            }
        })
    }
}