package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityDasHelp7Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class DasHelp7Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDasHelp7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDasHelp7Binding.inflate(layoutInflater)

        initEventListener()

        setContentView(R.layout.activity_das_help7)
    }

    private fun initEventListener() {
        binding.tvDasHelp7.setOnClickListener {
            finish()
            startActivity(Intent(this@DasHelp7Activity, DasHelp8Activity::class.java))
        }

        binding.clDasHelp7.setOnTouchListener(object : OnSwipeTouchListener(this@DasHelp7Activity) {
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@DasHelp7Activity, DasHelp8Activity::class.java))
            }
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@DasHelp7Activity, DasHelp6Activity::class.java))
            }
        })
    }
}