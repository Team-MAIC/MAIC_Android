package com.maic.kurlyhack.feature.help

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityPickingHelp4Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp4Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvPickHelp4.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp4Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp4.setOnTouchListener(object : OnSwipeTouchListener(this@PickingHelp4Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp4Activity, PickingHelp5Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp4Activity, PickingHelp3Activity::class.java))
            }
        })
    }
}
