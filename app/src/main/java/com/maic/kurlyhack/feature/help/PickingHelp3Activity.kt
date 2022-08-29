package com.maic.kurlyhack.feature.help

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maic.kurlyhack.databinding.ActivityPickingHelp3Binding
import com.maic.kurlyhack.feature.OnSwipeTouchListener

class PickingHelp3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPickingHelp3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickingHelp3Binding.inflate(layoutInflater)
        initEventListener()
        setContentView(binding.root)
    }

    private fun initEventListener() {
        binding.tvPickHelp3.setOnClickListener {
            finish()
            startActivity(Intent(this@PickingHelp3Activity, PickingHelp8Activity::class.java))
        }

        binding.clPickHelp3.setOnTouchListener(object : OnSwipeTouchListener(this@PickingHelp3Activity) {
            override fun onSwipeLeft() {
                finish()
                startActivity(Intent(this@PickingHelp3Activity, PickingHelp4Activity::class.java))
            }
            override fun onSwipeRight() {
                finish()
                startActivity(Intent(this@PickingHelp3Activity, PickingHelp2Activity::class.java))
            }
        })
    }
}
