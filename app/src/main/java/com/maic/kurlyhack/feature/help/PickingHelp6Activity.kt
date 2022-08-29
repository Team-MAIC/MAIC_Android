package com.maic.kurlyhack.feature.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maic.kurlyhack.R
import com.maic.kurlyhack.databinding.ActivityPickingHelp6Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp6Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvPickHelp6.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp6Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp6.setOnTouchListener(object: OnSwipeTouchListener(this@PickingHelp6Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp6Activity, PickingHelp7Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp6Activity, DasHelp5Activity::class.java))
            }
        })
    }
}