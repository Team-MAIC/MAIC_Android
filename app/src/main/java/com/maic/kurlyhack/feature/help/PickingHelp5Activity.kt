package com.maic.kurlyhack.feature.help

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityPickingHelp5Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp5Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvPickHelp5.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp5Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp5.setOnTouchListener(object : OnSwipeTouchListener(this@PickingHelp5Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp5Activity, PickingHelp6Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp5Activity, DasHelp4Activity::class.java))
            }
        })
    }
}
