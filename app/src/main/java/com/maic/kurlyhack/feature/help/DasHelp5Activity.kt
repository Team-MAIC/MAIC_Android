package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp5Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp5Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_das_help5)
    }

    private fun initEventListener() {
        binding.tvDasHelp5.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp5Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp5.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp5Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp5Activity, DasHelp6Activity::class.java))
            }
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp5Activity, DasHelp4Activity::class.java))
            }
        })
    }
}