package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp6Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp6Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvDasHelp6.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp6Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp6.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp6Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp6Activity, DasHelp7Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp6Activity, DasHelp5Activity::class.java))
            }
        })
    }
}